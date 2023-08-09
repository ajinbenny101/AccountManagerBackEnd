package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Interests;
import com.fdmgroup.springauth.repository.InterestsRepository;

@Service
public class InterestsService {
	private final InterestsRepository interestRepo;
	
	public InterestsService(InterestsRepository interestRepo) {
		super();
		this.interestRepo = interestRepo;
	}

	public List<Interests> allInterests() {
		return interestRepo.findAll();
	}

	public Interests getInterestByInterestCode(String id) {
		Optional<Interests> optionalInterest = interestRepo.findByInterestCode(id);
		if (optionalInterest.isPresent()) {
		return optionalInterest.get();
		} else return null;
	}

	public Interests updateInterest(Interests interest) {
		if (interestRepo.existsById(interest.getInterestCode())) {
			return interestRepo.save(interest);
		}
		return null;
	}

	public Interests addInterest(Interests interest) {
		Optional<Interests> optionalInterest = interestRepo.findById(interest.getInterestCode());
		if(optionalInterest.isPresent()) {
			
		}
		
		return interestRepo.save(interest);
	}

	public void deleteInterestByInterestCode(String id) {
		if (interestRepo.existsById(id)) {
			interestRepo.deleteById(id);
		}
	}

}
