package com.fdmgroup.springauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.springauth.model.JobField;

public interface JobFieldRepository extends JpaRepository<JobField, Integer>{
	
}
