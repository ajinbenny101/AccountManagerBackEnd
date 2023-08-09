package com.fdmgroup.springauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Streams {
	@Id
	@Column(name="stream_code")
	private String streamCode;
	private String stream;
	@Column(name="stream_pathway")
	private String streamPathway;
	@OneToMany(mappedBy="stream")
	private List<Placements> placements;
	@OneToMany(mappedBy="stream")
	private List<Consultants> consultants;
	
	public Streams(String streamCode, String stream, String streamPathway, List<Placements> placements,
			List<Consultants> consultants) {
		super();
		this.streamCode = streamCode;
		this.stream = stream;
		this.streamPathway = streamPathway;
		this.placements = placements;
		this.consultants = consultants;
	}

	public Streams() {
		super();
	}

	public String getStreamCode() {
		return streamCode;
	}

	public void setStreamCode(String streamCode) {
		this.streamCode = streamCode;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getStreamPathway() {
		return streamPathway;
	}

	public void setStreamPathway(String streamPathway) {
		this.streamPathway = streamPathway;
	}
	@JsonIgnore
	public List<Placements> getPlacements() {
		return placements;
	}

	public void setPlacements(List<Placements> placements) {
		this.placements = placements;
	}
	@JsonIgnore
	public List<Consultants> getConsultants() {
		return consultants;
	}

	public void setConsultants(List<Consultants> consultants) {
		this.consultants = consultants;
	}
	
	
	
}
