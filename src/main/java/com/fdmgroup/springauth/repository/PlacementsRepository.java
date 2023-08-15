package com.fdmgroup.springauth.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.Placements;

public interface PlacementsRepository extends JpaRepository<Placements, Integer> {
	boolean existsByNameOfCompanyAndJobTitleAndStartDateAndEndDateAndLocation(
	        String nameOfCompany, String jobTitle, Date startDate, Date endDate,
	        String location);

}

	
