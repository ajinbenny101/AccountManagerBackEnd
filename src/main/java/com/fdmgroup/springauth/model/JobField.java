package com.fdmgroup.springauth.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="job_fields")
public class JobField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_field_id")
	private int id;
	@Column(name="job_field")
	private String field;
	@OneToMany(mappedBy="jobField")
	private List<Placements> placements;
	public JobField(int id, String field, List<Placements> placements) {
		super();
		this.id = id;
		this.field = field;
		this.placements = placements;
	}
	public JobField() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setJobField(String field) {
		this.field = field;
	}
	public List<Placements> getPlacements() {
		return placements;
	}
	public void setPlacements(List<Placements> placements) {
		this.placements = placements;
	}
	
}
