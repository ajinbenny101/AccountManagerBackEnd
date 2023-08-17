package com.fdmgroup.springauth.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fdmgroup.springauth.dto.NewQualificationsDto;
import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.repository.ConsultantsRepository;

@Component
public class QualificationsMapper {
	private ConsultantsRepository consultantsRepo;
	
	public QualificationsMapper(ConsultantsRepository consultantsRepo) {
		this.consultantsRepo = consultantsRepo;
	}
	
	public Qualifications toEntity(NewQualificationsDto qualificationDto) {
		Qualifications newQualification = new Qualifications();
		newQualification.setId(qualificationDto.getId());
		newQualification.setQualificationType(qualificationDto.getQualificationType());
		newQualification.setDateAwarded(qualificationDto.getDateAwarded());
		newQualification.setGrade(qualificationDto.getGrade());
		newQualification.setInstitution(qualificationDto.getInstitution());
		
		Consultants consultantReturn;
		Consultants consultant = qualificationDto.getConsultant();
		
		Optional<Consultants> optConsultant = consultantsRepo.findById(consultant.getId());
		if(optConsultant.isPresent()) {
			consultantReturn = optConsultant.get();
		} else {
			consultantReturn = qualificationDto.getConsultant();
		}
		newQualification.setConsultant(consultantReturn);
		
		return newQualification;
	}
}
