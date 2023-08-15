package com.fdmgroup.springauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.Interests;


public interface InterestsRepository extends JpaRepository<Interests, String>{
	Optional<Interests> findByInterestCode(String interestCode);
}
