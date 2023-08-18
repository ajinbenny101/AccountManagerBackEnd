package com.fdmgroup.springauth.dto;

import java.sql.Date;
import java.util.List;

import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.model.Streams;

public class NewPlacementsDto {
	private int id;
	private String nameOfCompany;
	private String jobTitle;
	private Date startDate;
	private Date endDate;
	private String location;
	private byte ongoing;
	private Consultants consultant;
	private List<Skills> skills;
	private JobField jobField;
	private Streams stream;
	private Date postedOn;
	private Date placementFilledOn;
	
	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Date getPlacementFilledOn() {
		return placementFilledOn;
	}

	public void setPlacementFilledOn(Date placementFilledOn) {
		this.placementFilledOn = placementFilledOn;
	}

	public NewPlacementsDto() {
		super();
	}

	public String getNameOfCompany() {
		return nameOfCompany;
	}

	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public byte getOngoing() {
		return ongoing;
	}

	public void setOngoing(byte ongoing) {
		this.ongoing = ongoing;
	}

	public Consultants getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultants consultant) {
		this.consultant = consultant;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public JobField getJobField() {
		return jobField;
	}

	public void setJobField(JobField jobField) {
		this.jobField = jobField;
	}

	public Streams getStream() {
		return stream;
	}

	public void setStream(Streams stream) {
		this.stream = stream;
	}
	
}
