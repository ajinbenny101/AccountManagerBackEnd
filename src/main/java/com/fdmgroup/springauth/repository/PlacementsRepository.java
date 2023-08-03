package com.fdmgroup.springauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Placements;

public interface PlacementsRepository extends JpaRepository<Placements, Integer> {
//Optional<Placements> findByFirstNameAndLastNameAndFdmEmail(String firstName, String lastName, String fdmEmail);
}

	
