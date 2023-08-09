package com.fdmgroup.springauth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.service.JobFieldService;

@RestController
@RequestMapping("api/v1/jobfield")
public class JobFieldController {
	private final JobFieldService jobFieldService;

	public JobFieldController(JobFieldService jobFieldService) {
		super();
		this.jobFieldService = jobFieldService;
	}
	
	@GetMapping
	public ResponseEntity<List<JobField>> getAllJobFields(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(jobFieldService.allJobFields());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobField> getJobFieldById(@PathVariable int id){
		JobField jobField = jobFieldService.getJobFieldById(id);
		if (jobField != null) {
			return ResponseEntity.status(HttpStatus.OK).body(jobField);
		} else return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<JobField> updateJobField(@PathVariable int id, @RequestBody JobField jobField){
		jobField.setId(id);
		JobField updatedJobField = jobFieldService.updateJobField(jobField);
		if (updatedJobField != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updatedJobField);
		} else return null;
	}
	
	@PostMapping
	public ResponseEntity<JobField> addJobField(@RequestBody JobField jobField){
		JobField addedJobField = jobFieldService.addJobField(jobField);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedJobField.getId())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(addedJobField);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJobFieldById(@PathVariable int id){
		jobFieldService.deleteJobFieldById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
