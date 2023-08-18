package com.fdmgroup.springauth.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fdmgroup.springauth.dto.NewPlacementsDto;
import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.model.Placements;
import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.model.Streams;
import com.fdmgroup.springauth.repository.ConsultantsRepository;
import com.fdmgroup.springauth.repository.JobFieldRepository;
import com.fdmgroup.springauth.repository.SkillsRepository;
import com.fdmgroup.springauth.repository.StreamsRepository;

@Component
public class PlacementsMapper {
	private ConsultantsRepository consultantRepo;
	private SkillsRepository skillsRepo;
	private JobFieldRepository jobFieldRepo;
	private StreamsRepository streamsRepo;
	
	public PlacementsMapper(ConsultantsRepository consultantRepo, SkillsRepository skillsRepo,
			JobFieldRepository jobFieldRepo, StreamsRepository streamsRepo) {
		this.consultantRepo = consultantRepo;
		this.skillsRepo = skillsRepo;
		this.jobFieldRepo = jobFieldRepo;
		this.streamsRepo = streamsRepo;
	}
	
	public Placements toEntity(NewPlacementsDto placementDto) {
		Placements newPlacement = new Placements();
		
		newPlacement.setNameOfCompany(placementDto.getNameOfCompany());
		newPlacement.setJobTitle(placementDto.getJobTitle());
		newPlacement.setStartDate(placementDto.getStartDate());
		newPlacement.setEndDate(placementDto.getEndDate());
		newPlacement.setLocation(placementDto.getLocation());
		newPlacement.setOngoing(placementDto.getOngoing());
		
		//consultant
		try {
		Consultants consultantReturn;
		Consultants consultant1 = placementDto.getConsultant();
		Optional<Consultants> optConsultant = consultantRepo.findById(consultant1.getId());
		if (optConsultant.isPresent()) {
			consultantReturn = optConsultant.get();
		} else {
			consultantReturn = placementDto.getConsultant();
		}
		newPlacement.setConsultant(consultantReturn);} catch (NullPointerException e){
			
		};
		
		//skills
		try {
		List<Skills> skillsReturn = new ArrayList<>();
		List<Skills> skill1 = placementDto.getSkills();
		for (Skills skill: skill1) {
			Optional<Skills> optSkill = skillsRepo.findById(skill.getId());
			if (optSkill.isPresent()) {
				skillsReturn.add(optSkill.get());
			} else {
				skillsReturn.add(skill);
			}
		}
		newPlacement.setSkills(skillsReturn);} catch (NullPointerException e){
			
		};
		
		//jobField
		try {
		JobField jobFieldReturn;
		JobField jobField1 = placementDto.getJobField();
		Optional<JobField> optJobField = jobFieldRepo.findById(jobField1.getId());
		if(optJobField.isPresent()) {
			jobFieldReturn = optJobField.get();
		} else {
			jobFieldReturn = placementDto.getJobField();
		}
		newPlacement.setJobField(jobFieldReturn);} catch (NullPointerException e) {
			
		};
		
		//stream
		try {
		Streams streamReturn;
		Streams stream1 = placementDto.getStream();
		Optional<Streams> optStream = streamsRepo.findByStreamCode(stream1.getStreamCode());
		if (optStream.isPresent()) {
			streamReturn = optStream.get();
		} else {
			streamReturn = placementDto.getStream();
		}
		newPlacement.setStream(streamReturn);} catch (NullPointerException e) {
			
		};
		
		return newPlacement;
	}
	
}
