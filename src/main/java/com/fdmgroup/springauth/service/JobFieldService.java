package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.repository.JobFieldRepository;

public class JobFieldService {
	private final JobFieldRepository jobFieldRepo;
	
	public JobFieldService(JobFieldRepository jobFieldRepo) {
		super();
		this.jobFieldRepo = jobFieldRepo;
	}

	public List<JobField> allJobFields() {
		return jobFieldRepo.findAll();
	}

	public JobField getJobFieldById(int id) {
		Optional<JobField> optionalJobField = jobFieldRepo.findById(id);
		if (optionalJobField.isPresent()) {
			return optionalJobField.get();
		}
		return null;
	}

	public JobField updateJobField(JobField jobField) {
		if (jobFieldRepo.existsById(jobField.getId())) {
			return jobFieldRepo.save(jobField); 
		}
		else return null;
	}

	public JobField addJobField(JobField jobField) {
		return jobFieldRepo.save(jobField);
	}

	public void deleteJobFieldById(int id) {
		if (jobFieldRepo.existsById(id)) {
			jobFieldRepo.deleteById(id);
		}
	}

}
