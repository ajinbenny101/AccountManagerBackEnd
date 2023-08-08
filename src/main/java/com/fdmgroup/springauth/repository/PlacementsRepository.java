package com.fdmgroup.springauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.Placements;

public interface PlacementsRepository extends JpaRepository<Placements, Integer> {
	boolean existsByNameOfCompanyAndJobTitleAndStartDateAndEndDateAndExpectedEndDateAndPostedOnAndPlacementFilledOnAndLocation(
	        String nameOfCompany, String jobTitle, String startDate, String endDate,
	        String expectedEndDate, String postedOn, String placementFilledOn, String location);

}

	
