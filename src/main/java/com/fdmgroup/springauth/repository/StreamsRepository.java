package com.fdmgroup.springauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.springauth.model.Streams;

@Repository
public interface StreamsRepository extends JpaRepository<Streams, Integer>{

}
