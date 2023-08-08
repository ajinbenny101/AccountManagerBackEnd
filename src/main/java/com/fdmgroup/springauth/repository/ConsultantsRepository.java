package com.fdmgroup.springauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Consultants;


public interface ConsultantsRepository extends JpaRepository<Consultants, Integer> {
	Optional<Consultants> findByFirstNameAndLastNameAndFdmEmail(String firstName, String lastName, String fdmEmail);

	Optional<List<Consultants>>findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
	Optional<List<Consultants>>findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);
	 List<Consultants> findByGeoflexId(int geoflexId);
}
