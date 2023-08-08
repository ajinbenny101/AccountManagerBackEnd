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

import com.fdmgroup.springauth.model.Interests;
import com.fdmgroup.springauth.service.InterestsService;

@RestController
@RequestMapping("api/v1/interests")
public class InterestsController {
	
	private final InterestsService interestService;

	public InterestsController(InterestsService interestService) {
		super();
		this.interestService = interestService;
	}
	
	@GetMapping
	public ResponseEntity<List<Interests>> getAllConsultants(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(interestService.allInterests());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Interests> getById(@PathVariable String id){
		Interests interest = interestService.getInterestByInterestCode(id);
		if (interest != null) {
			return ResponseEntity
				.status(HttpStatus.OK)
				.body(interest);
		} else return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Interests> updateInterest(@PathVariable String id, @RequestBody Interests interest){
		interest.setInterestCode(id);
		Interests updatedInterest = interestService.updateInterest(interest);
		if (updatedInterest != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updatedInterest);
		} else return null;
		
	}
	
	@PostMapping
	public ResponseEntity<Interests> addInterest(@RequestBody Interests interest){
		Interests addedInterest = interestService.addInterest(interest);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedInterest.getInterestCode())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(addedInterest);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInterest(@PathVariable String id){
		interestService.deleteInterestByInterestCode(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
