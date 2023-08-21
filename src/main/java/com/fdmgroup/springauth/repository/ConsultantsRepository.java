package com.fdmgroup.springauth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.model.Skills;


public interface ConsultantsRepository extends JpaRepository<Consultants, Integer> {
	Optional<Consultants> findByFirstNameAndLastNameAndFdmEmail(String firstName, String lastName, String fdmEmail);

	Optional<List<Consultants>>findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
	Optional<List<Consultants>>findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);
	List<Consultants> findByGeoflexId(int geoflexId);
	
//	//Will only work with consultants who have past placements, need to refactor to include consultants who have had no placements
//	@Query("SELECT c FROM consultants c " +
//	           "WHERE p.ongoing = false " +
//	           "AND p.end_date = (SELECT MAX(p.end_date) FROM placements p " +
//	           "                  WHERE p.consultants = c)")
//    @Query("SELECT DISTINCT c FROM Consultants c " +
//            "LEFT JOIN c.placements p " +
//            "WHERE p IS NULL OR NOT p.ongoing")
    @Query("SELECT c FROM Consultants c " +
            "WHERE NOT EXISTS (" +
            "    SELECT 1 FROM c.placements p " +
            "    WHERE p.ongoing = true" +
            ") OR c.placements IS EMPTY")
    List<Consultants> findConsultantsWithLatestNonOngoingPlacement();
    
    List<Consultants> findBySkillsIn(List<Skills> skills);
    
    List<Consultants> findByQualificationsIn(List<Qualifications> qualifications);
}
