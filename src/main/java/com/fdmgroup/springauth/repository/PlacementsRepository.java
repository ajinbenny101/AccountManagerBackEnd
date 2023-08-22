package com.fdmgroup.springauth.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.model.Placements;

public interface PlacementsRepository extends JpaRepository<Placements, Integer> {
	boolean existsByNameOfCompanyAndJobTitleAndStartDateAndEndDateAndLocation(
	        String nameOfCompany, String jobTitle, Date startDate, Date endDate,
	        String location);
	 List<Placements> findByStartDateAfter(Date date);
	 List<Placements> findByPlacementFilledOnIsNull();
	 List<Placements> findByJobField(JobField jobField);


}

	
