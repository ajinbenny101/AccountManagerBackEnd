package com.fdmgroup.springauth.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Placements {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placementsgen")
	//@SequenceGenerator(name = "placementsgen", sequenceName = "placements_id_seq", allocationSize = 1)
	@Column(name = "placement_id", columnDefinition = "INT")
	private int id;
	@Column(name = "name_company")
	private String nameOfCompany;
	@Column(name = "job_title")
	private String jobTitle;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	//@Column(name = "expected_end_date")
	//private String expectedEndDate;
	@Column(name = "posted_on")
	private Date postedOn;
	@Column(name = "placement_filled_on")
	private Date placementFilledOn;
	private String location;
	private byte ongoing;
	@ManyToOne
	@JoinColumn(name="consultant_id", nullable=true)
	private Consultants consultant;
	@ManyToMany
	@JoinTable(
			name="placement_skills",
			joinColumns = @JoinColumn(name="placement_id"),
			inverseJoinColumns= @JoinColumn(name="skill_id")
			)
	private List<Skills> skills;
	@ManyToOne
	@JoinColumn(name="job_field_id")
	private JobField jobField;
	@ManyToOne
	@JoinColumn(name="stream_code")
	private Streams stream;
	
	@JsonIgnore
	public Streams getStream() {
		return stream;
	}

	public void setStream(Streams stream) {
		this.stream = stream;
	}

	public Placements(int id, String nameOfCompany, String jobTitle, Date startDate, Date endDate,
			/*String expectedEndDate,*/ Date postedOn, Date placementFilledOn, String location, byte ongoing,
			Consultants consultants) {
		super();
		this.id = id;
		this.nameOfCompany = nameOfCompany;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		//this.expectedEndDate = expectedEndDate;
		this.postedOn = postedOn;
		this.placementFilledOn = placementFilledOn;
		this.location = location;
		this.ongoing = ongoing;
		this.consultant = consultants;
	}
	
	@JsonIgnore
	public Consultants getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultants consultant) {
		this.consultant = consultant;
	}
	@JsonIgnore
	public JobField getJobField() {
		return jobField;
	}

	public void setJobField(JobField jobField) {
		this.jobField = jobField;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public Placements() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	//public String getExpectedEndDate() {
		//return expectedEndDate;
	//}

	//public void setExpectedEndDate(String expectedEndDate) {
		//this.expectedEndDate = expectedEndDate;
	//}

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

	//json ignore to stop recursion, will need to create a diff way to get
	@JsonIgnore
	public Consultants getConsultants() {
		return consultant;
	}

	public List<Skills> getSkills() {
		return skills;
	}
	
	
	
}
