package com.fdmgroup.springauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Skills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skillgen")
	//@SequenceGenerator(name = "skillgen", sequenceName = "skill_id_seq", allocationSize = 1)
	@Column(name="skill_id", columnDefinition = "INT")
	private long id;
	private String ability;
	@Column(name="skill_name")
	private String skillName;
	@ManyToMany(mappedBy="skills")
	List<Consultants> consultants;
	@ManyToMany(mappedBy="skills")
	List<Placements> placements;
	
	public Skills(long id, String ability, String skillName, List<Consultants> consultants,
			List<Placements> placements) {
		super();
		this.id = id;
		this.ability = ability;
		this.skillName = skillName;
		this.consultants = consultants;
		this.placements = placements;
	}

	public Skills() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@JsonIgnore
	public List<Consultants> getConsultants() {
		return consultants;
	}
	
	@JsonIgnore
	public List<Placements> getPlacements() {
		return placements;
	}
	
	//Json ignores to stop recursion, new diff methods 
}
