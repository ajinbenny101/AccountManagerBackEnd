package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.repository.SkillsRepository;

@Service
public class SkillsService {
	private final SkillsRepository skillsRepo;

	public SkillsService(SkillsRepository skillsRepo) {
		super();
		this.skillsRepo = skillsRepo;
	}

	public List<Skills> allSkills() {
		return skillsRepo.findAll();
	}
	
	public Skills getSkillById(int id) {
		Optional<Skills> optionalSkill = skillsRepo.findById(id);
		if (optionalSkill.isPresent()) {
			return optionalSkill.get();
		}
		return null;
	}

	public Skills updateSkill(Skills skill) {
		if (skillsRepo.existsById(skill.getId())) {
			return skillsRepo.save(skill);
		} else {
			//TODO error
		}
		return null;
	}
	
	public Skills addSkill(Skills skill) {
		return skillsRepo.save(skill);
	}
	
	public void deleteSkillById(int id) {
		if (skillsRepo.existsById(id)) {
			skillsRepo.deleteById(id);
		} else {
			//TODO error
		}
	}
}
