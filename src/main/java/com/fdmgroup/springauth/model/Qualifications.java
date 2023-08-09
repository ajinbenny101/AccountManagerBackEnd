package com.fdmgroup.springauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Qualifications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// May need to delete sequence generator and edit generatedValue to work with mySQL autoincrement if we switch over
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qualificationsgen")
	//@SequenceGenerator(name = "qualificationsgen", sequenceName = "qualifications_id_seq", allocationSize = 1)
	@Column(name = "qualification_id", columnDefinition = "INT")
	private long id;
	@Column(name = "qualification_type")
	private String qualificationType;
	@Column(name = "year_awarded")
	private int dateAwarded;
	private String grade;
	@Column(name = "qualification_name")
	private String qualificationName;
	private String institution; 
	@ManyToOne
	@JoinColumn(name="consultant_id", nullable=false)
	private Consultants consultant;

	
	public Qualifications() {
		super();
	}

	public Qualifications(String qualificationType, int dateAwarded, String grade, String qualificationName,
			String institution, Consultants consultant) {
		super();
		this.qualificationType = qualificationType;
		this.dateAwarded = dateAwarded;
		this.grade = grade;
		this.qualificationName = qualificationName;
		this.institution = institution;
		this.consultant = consultant;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(String qualificationType) {
		this.qualificationType = qualificationType;
	}

	public int getDateAwarded() {
		return dateAwarded;
	}

	public void setDateAwarded(int dateAwarded) {
		this.dateAwarded = dateAwarded;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}



	
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@JsonIgnore
	public Consultants getConsultant() {
		return consultant;
	}
	// used to stop recurssion error. TODO getconsultant via a different method with name id etc

	public void setConsultant(Consultants consultant) {
		this.consultant = consultant;
	}
}
