package com.fdmgroup.springauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Geoflex {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geoflexgen")
	//@SequenceGenerator(name = "geoflexgen", sequenceName = "geoflex_id_seq", allocationSize = 1)
	@Column(name = "geoflex_id", columnDefinition = "INT")
	private long id;
	private String region;
	@ManyToMany(mappedBy="geoflex")
	private List<Consultants> consultants = new ArrayList<>();
	
	public Geoflex() {
		super();
	}

	public Geoflex(long id, String region) {
		super();
		this.id = id;
		this.region = region;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@JsonIgnore
	public List<Consultants> getConsultants() {
		return consultants;
	}
	//TODO create get consultant full name and id this was ignored to stop recursion error

	public void setConsultants(List<Consultants> consultants) {
		this.consultants = consultants;
	}
	
	
	
}
