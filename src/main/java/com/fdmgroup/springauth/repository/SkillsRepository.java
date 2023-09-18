package com.fdmgroup.springauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.springauth.model.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer>{

	Optional<List<Skills>> findBySkillName(String name);

}
