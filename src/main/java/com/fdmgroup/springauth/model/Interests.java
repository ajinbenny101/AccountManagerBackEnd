package com.fdmgroup.springauth.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Interests {
	@Id
	@Column(name="interest_code")
	private String interestCode;
	private String interest;
	@ManyToMany(mappedBy = "interests")
	private List<Consultants> consultants;
	public String getInterestCode() {
		return interestCode;
	}
	public void setInterestCode(String interestCode) {
		this.interestCode = interestCode;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	@JsonIgnore
	public List<Consultants> getConsultants() {
		return consultants;
	}
	public void setConsultants(List<Consultants> consultants) {
		this.consultants = consultants;
	}
	public Interests(String interestCode, String interest, List<Consultants> consultants) {
		super();
		this.interestCode = interestCode;
		this.interest = interest;
		this.consultants = consultants;
	}
	public Interests() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
