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

import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.service.SkillsService;

@RestController
@RequestMapping("api/v1/skills")
public class SkillsController {
	private final SkillsService skillsService;

	public SkillsController(SkillsService skillsService) {
		super();
		this.skillsService = skillsService;
	}
	
	@GetMapping
	public ResponseEntity<List<Skills>> getAllSkills(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(skillsService.allSkills());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Skills> getSkillbyId(@PathVariable int id){
		Skills skill = skillsService.getSkillById(id);
		if (skill != null) {
			return ResponseEntity.status(HttpStatus.OK).body(skill);
		} else {
			return null;
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Skills> updateSkill(@PathVariable int id, @RequestBody Skills skill){
		skill.setId(id);
		Skills updatedSkill = skillsService.updateSkill(skill);
		if (updatedSkill != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updatedSkill);
		} else {
			return null;
		}
	}
	
	@PostMapping
	public ResponseEntity<Skills> addSkill(@RequestBody Skills skill){
		Skills addedSkill = skillsService.addSkill(skill);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(addedSkill.getId())
				.toUri();
		return ResponseEntity
				.created(location)
				.body(addedSkill);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSkillById(@PathVariable int id){
		skillsService.deleteSkillById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
