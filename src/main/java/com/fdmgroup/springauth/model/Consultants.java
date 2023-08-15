package com.fdmgroup.springauth.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Consultants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consultant_id", columnDefinition = "INT")
	private int id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "mobile_number")
	private String phoneNumber;
	@Column(name = "fdm_email")
	private String fdmEmail;
	@Column(name = "consultant_type")
	private String type;
	@ManyToMany
	@JoinTable(
			name = "consultant_geoflex",
			joinColumns = @JoinColumn(name="consultant_id"),
			inverseJoinColumns=@JoinColumn(name="geoflex_id"))
	private List<Geoflex> geoflex;
	@OneToMany(mappedBy="consultant")
	private List<Qualifications> qualifications;
	@ManyToMany
	@JoinTable(
			name = "consultant_skills",
			joinColumns = @JoinColumn(name="consultant_id"),
			inverseJoinColumns=@JoinColumn(name="skill_id")
			)
	private List<Skills> skills;
	
	@OneToMany(mappedBy="consultant")
	private List<Placements> placements;
	@ManyToOne
	@JoinColumn(name="stream_code")
	private Streams stream;
	private String postcode;
	private String cv;
	@ManyToMany
	@JoinTable(
			name="consultant_interests",
			joinColumns=@JoinColumn(name="consultant_id"),
			inverseJoinColumns=@JoinColumn(name="interest_code")
			)
	private List<Interests> interests;
	
	
	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public List<Geoflex> getGeoflex() {
		return geoflex;
	}

	public void setGeoflex(List<Geoflex> geoflex) {
		this.geoflex = geoflex;
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

	public Consultants() {
		super();
	}

	public Consultants(String firstName, String lastName, String phoneNumber, String fdmEmail, String geoFlex,
			String type, ArrayList<Consultants> consultants) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.fdmEmail = fdmEmail;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Geoflex> getGeoFlex() {
		return geoflex;
	}

	public void setGeoFlex(List<Geoflex> geoFlex) {
		this.geoflex = geoFlex;
	}

	public List<Qualifications> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<Qualifications> qualifications) {
		this.qualifications = qualifications;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Placements> getPlacements() {
		return placements;
	}

	public void setPlacements(List<Placements> placements) {
		this.placements = placements;
	}



}
