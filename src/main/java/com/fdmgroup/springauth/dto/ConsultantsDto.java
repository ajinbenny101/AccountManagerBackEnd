package com.fdmgroup.springauth.dto;

import java.util.List;

import com.fdmgroup.springauth.model.Geoflex;
import com.fdmgroup.springauth.model.Interests;
import com.fdmgroup.springauth.model.Placements;
import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.model.Streams;


public class ConsultantsDto {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String fdmEmail;
	private String type;
	private List<Integer> geoflex;
	//maybe need to change this back to type qualifications to add new types when inputing new consultants,
	
	private List<Qualifications> qualifications;
	private List<Integer> skills;
	private List<Placements> placements;
	private Streams stream;
	private String postcode;
	private String cv;
	private List<String> interests;
	
	public ConsultantsDto() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFdmEmail() {
		return fdmEmail;
	}
	public void setFdmEmail(String fdmEmail) {
		this.fdmEmail = fdmEmail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Integer> getGeoflex() {
		return geoflex;
	}
	public void setGeoflex(List<Integer> geoflex) {
		this.geoflex = geoflex;
	}
	public List<Qualifications> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<Qualifications> qualifications) {
		this.qualifications = qualifications;
	}
	public List<Integer> getSkills() {
		return skills;
	}
	public void setSkills(List<Integer> skills) {
		this.skills = skills;
	}
	public List<Placements> getPlacements() {
		return placements;
	}
	public void setPlacements(List<Placements> placements) {
		this.placements = placements;
	}
	public Streams getStream() {
		return stream;
	}
	public void setStream(Streams stream) {
		this.stream = stream;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	
	
}
