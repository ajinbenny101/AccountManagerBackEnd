package com.fdmgroup.springauth.dto;

import com.fdmgroup.springauth.model.Consultants;

public class NewQualificationsDto {
	private String qualificationType;

    private int dateAwarded;

    private String grade;

    private String qualificationName;

    private String institution;

    private Consultants consultant;

 

    

    public NewQualificationsDto() {

        super();

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

 

    public Consultants getConsultant() {

        return consultant;

    }

 

    public void setConsultant(Consultants consultant) {

        this.consultant = consultant;

    }

 

    public String getQualificationName() {

        return qualificationName;

    }

 

    public void setQualificationName(String qualificationName) {

        this.qualificationName = qualificationName;

    }
}
