package com.fdmgroup.springauth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.exceptions.ExistsException;
import com.fdmgroup.springauth.exceptions.NotFoundException;
import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.service.ConsultantsService;



@RestController
@RequestMapping("api/v1/consultants")

public class ConsultantsController {
	
	private final ConsultantsService consultantsService;
	
	public ConsultantsController(ConsultantsService consultantsService) {
		super();
		this.consultantsService = consultantsService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Consultants>> getAllConsultants(){
		return ResponseEntity
		       .status(HttpStatus.OK)
		       .body(consultantsService.allConsultants());
	}


	@GetMapping("/{id}")
    public ResponseEntity<Consultants> getConsultantById(@PathVariable int id) throws NotFoundException {
        Consultants consultant = consultantsService.getConsultantById(id);
        if (consultant != null) {
            return ResponseEntity.status(HttpStatus.OK).body(consultant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Consultants> updateConsultant(@PathVariable int id, @RequestBody Consultants consultant) throws NotFoundException {
        consultant.setId(id);
        Consultants updatedConsultant = consultantsService.updateConsultant(consultant);
        if (updatedConsultant != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedConsultant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	@PostMapping
	public ResponseEntity<Consultants> addConsultant(@RequestBody Consultants consultant) throws ExistsException {
	Consultants addedConsultant = consultantsService.addConsultant(consultant);
	URI location = ServletUriComponentsBuilder
				   .fromCurrentRequest()
	               .path("/{id}")
	               .buildAndExpand(addedConsultant.getId())
	               .toUri();
	return ResponseEntity
			.created(location)
			.body(addedConsultant);
	}
	
	//We have changed the database to use on delete set null where the consultant is referenced as a foreign key elsewhere. 
	//This makes sense to keep placements even when you delete consultant but has also been done for qualifications, consultant_geoflex etc.. which may need to be deleted too.
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConsultantById(@PathVariable int id) throws NotFoundException {
	consultantsService.deleteConsultantById(id);
	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Consultants>> searchConsultantsByName(@RequestParam("name") String name) {
        List<Consultants> searchResults = consultantsService.findByName(name);

        if (!searchResults.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(searchResults);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
    }
	 @GetMapping("/geoflex/{geoflexId}")
	    public ResponseEntity<List<Consultants>> getConsultantsByGeoflexId(@PathVariable int geoflexId) {
	        List<Consultants> consultants = consultantsService.findByGeoflexId(geoflexId);

	        if (!consultants.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.OK).body(consultants);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 
	 @GetMapping("/beached")
	 public ResponseEntity<List<Consultants>> getBeachedConsultants(){
			return ResponseEntity
				       .status(HttpStatus.OK)
				       .body(consultantsService.getBeachedConsultants());
	 }

}
