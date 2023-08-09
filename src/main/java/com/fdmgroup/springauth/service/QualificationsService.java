package com.fdmgroup.springauth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.repository.QualificationsRepository;

@Service
public class QualificationsService {
	private final QualificationsRepository qualificationsRepo;
	
	public QualificationsService(QualificationsRepository qualificationsRepo) {
		this.qualificationsRepo = qualificationsRepo;
	}
	public List<Qualifications> allQualifications() {
		return qualificationsRepo.findAll();
	}
	public Qualifications getQualificationById(int id) {
		return qualificationsRepo.findById(id).get();
	}
	public Qualifications AddQualification(Qualifications qualification) {
		return qualificationsRepo.save(qualification);
	}
	public Qualifications updateQualification(Qualifications qualification) {
		return qualificationsRepo.save(qualification);
	}
	public void deleteQualificationById(int id) {
		if (qualificationsRepo.existsById(id)) {
			qualificationsRepo.deleteById(id);
		} 
	}

}
