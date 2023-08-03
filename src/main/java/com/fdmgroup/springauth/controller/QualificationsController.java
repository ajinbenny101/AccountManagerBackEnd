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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.service.QualificationsService;

@RestController
@RequestMapping("api/v1/qualifications")
public class QualificationsController {
	
	private final QualificationsService qualificationsService;
	
	public QualificationsController(QualificationsService qualificationsService) {
		super();
		this.qualificationsService = qualificationsService;
	}
	
	@GetMapping
	public ResponseEntity<List<Qualifications>> getAllQualifications(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(qualificationsService.allQualifications());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Qualifications> getQualificationById(@PathVariable int id) {
		Qualifications qualification = qualificationsService.getQualificationById(id);
		return ResponseEntity.status(HttpStatus.OK).body(qualification);
	}
	
//	@GetMapping("/consultant/{id}")
//	
//	@GetMapping("/placement/{id}")
	
	@PostMapping
	public ResponseEntity<Qualifications> addQualification(@RequestBody Qualifications qualification) {
		Qualifications addedQualification = qualificationsService.AddQualification(qualification);
		
		URI location = ServletUriComponentsBuilder
				   .fromCurrentRequest()
	               .path("/{id}")
	               .buildAndExpand(addedQualification.getId())
	               .toUri();
		return ResponseEntity
			.created(location)
			.body(addedQualification);
	}
	
	@PutMapping("/id")
	public ResponseEntity<Qualifications> updateQualification(@PathVariable int id, @RequestBody Qualifications qualification){
		qualification.setId(id);
		Qualifications updatedQualification = qualificationsService.updateQualification(qualification);
		return ResponseEntity.status(HttpStatus.OK).body(updatedQualification);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQualificationById(@PathVariable int id){
		qualificationsService.deleteQualificationById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
//	useless code for git
}
	