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

import com.fdmgroup.springauth.dto.NewPlacementsDto;
import com.fdmgroup.springauth.exceptions.ExistsException;
import com.fdmgroup.springauth.exceptions.NotFoundException;
import com.fdmgroup.springauth.mapper.PlacementsMapper;
import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.model.Placements;
import com.fdmgroup.springauth.service.PlacementsService;

@RestController
@RequestMapping("api/v1/placements")
public class PlacementsController {

    private final PlacementsService placementsService;
    private final PlacementsMapper placementMapper;

    public PlacementsController(PlacementsService placementsService, PlacementsMapper placementMapper) {
        super();
        this.placementsService = placementsService;
        this.placementMapper = placementMapper;
    }

    @GetMapping
    public ResponseEntity<List<Placements>> getAllPlacements() {
        // Implement logic to get all placements from placementsService
        List<Placements> placements = placementsService.getAllPlacements();
        return ResponseEntity.status(HttpStatus.OK).body(placements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placements> getPlacementById(@PathVariable int id)throws NotFoundException {
        // Implement logic to get placement by id from placementsService
        Placements placement = placementsService.getPlacementById(id);
        if (placement != null) {
            return ResponseEntity.status(HttpStatus.OK).body(placement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placements> updatePlacement(@PathVariable int id, @RequestBody NewPlacementsDto placementDto)throws NotFoundException {
        // Implement logic to update placement by id using placementsService
        Placements placement = placementMapper.toEntity(placementDto);
    	placement.setId(id);
        Placements updatedPlacement = placementsService.updatePlacement(placement);
        if (updatedPlacement != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedPlacement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Placements> addPlacement(@RequestBody NewPlacementsDto placementDto) throws ExistsException{
        // Implement logic to add a new placement using placementsService
        Placements addedPlacement = placementsService.addPlacement(placementMapper.toEntity(placementDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedPlacement.getId())
                .toUri();
        return ResponseEntity
        		.created(location)
        		.body(addedPlacement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlacementById(@PathVariable int id) throws NotFoundException{
        // Implement logic to delete placement by id using placementsService
        placementsService.deletePlacementById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
    @GetMapping("/search")
    public ResponseEntity<List<Placements>> searchPlacements(@RequestParam("status") String status) {
        List<Placements> placements;

        if (status.equalsIgnoreCase("upcoming")) {
            placements = placementsService.getUpcomingPlacements();
        } else if (status.equalsIgnoreCase("notFilled")) {
            placements = placementsService.getNotFilledPlacements();
        } else {
            return ResponseEntity.badRequest().build(); // Invalid status parameter
        }

        return ResponseEntity.ok(placements);
    }
    @GetMapping("/search/byJobField")
    public ResponseEntity<List<Placements>> searchPlacementsByJobField(@RequestParam int jobFieldId) {
        JobField jobField = new JobField();
        jobField.setId(jobFieldId);
        List<Placements> placements = placementsService.getPlacementsByJobField(jobField);
        return new ResponseEntity<>(placements, HttpStatus.OK);
    }
}
