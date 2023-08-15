package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.exceptions.ExistsException;
import com.fdmgroup.springauth.exceptions.NotFoundException;
import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.repository.ConsultantsRepository;



@Service
public class ConsultantsService {
	private final ConsultantsRepository consultantsRepo;
	
	public ConsultantsService(ConsultantsRepository consultantsRepo) {
        this.consultantsRepo = consultantsRepo;
    }
	
public List<Consultants> allConsultants(){
		return consultantsRepo.findAll();
		
	}

	public Consultants getConsultantById(int id) throws NotFoundException  {
		Optional<Consultants> optionalConsultant = consultantsRepo.findById(id);

		if (optionalConsultant.isPresent()) {
			return optionalConsultant.get();
		}
		 else {
			 throw new NotFoundException("No Consultant with id of " + id);
		    }
	}

	
	
	public Consultants addConsultant(Consultants consultant) throws ExistsException {
        Optional<Consultants> optionalConsultant = consultantsRepo.findByFirstNameAndLastNameAndFdmEmail(
                consultant.getFirstName(), consultant.getLastName(), consultant.getFdmEmail());

        if (optionalConsultant.isPresent()) {
            throw new ExistsException(
                    "Consultant " + consultant.getFirstName() + " " + consultant.getLastName() + " " + consultant.getFdmEmail() + " already exists");
        }

        return consultantsRepo.save(consultant);
    }

	public Consultants updateConsultant(Consultants consultant) throws NotFoundException {
		if (consultantsRepo.existsById(consultant.getId())) {
			return consultantsRepo.save(consultant);
		} else {
			throw new NotFoundException("No Consultant with id  " + consultant.getId() + "too update");
	    }
	}

	public void deleteConsultantById(int id) throws NotFoundException {
		if (consultantsRepo.existsById(id)) {
			consultantsRepo.deleteById(id);
		} 
		else {
			throw new NotFoundException("No Consultant with id  " + id + " too delete");
		}
		
	}
	

	
	public List<Consultants> findByName(String name) {
        String[] names = name.split("\\s");
        if(names.length == 2) {
            if(consultantsRepo.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(names[0], names[1]).isPresent())
            return consultantsRepo.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(names[0], names[1]).get();

        } else {
            if(consultantsRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name).isPresent()) {
                return consultantsRepo.findByFirstNameIgnoreCaseOrLastNameIgnoreCase(name, name).get();

            }

        }
		return null;
    }
	 public List<Consultants> findByGeoflexId(int geoflexId) {
	        return consultantsRepo.findByGeoflexId(geoflexId);
	    }

		
	 
//		public List<Consultants> findConsultantsWithMatchingSkills(Placements placement) {
//			List<Consultants> allConsultants = consultantsRepo.findAll();
//			List<Skills> placementSkills = placement.getSkills();
//			
//			return allConsultants.stream()
//					.filter(consultant -> consultant.getSkills().stream()
//							.anyMatch(skill -> placementSkills.contains(skill)))
//					.sorted(Comparator.comparingLong(consultant-> 
//								calculateMatchingSkillsCount(consultant, placementSkills)
//							).reversed()
//							.thenComparing(Comparator.comparingInt(consultant -> consultant.getSkills().size()))
//							)
//							.collect(Collectors.toList());
//		}
//
//		private Long calculateMatchingSkillsCount(Consultants consultant, List<Skills> skills) {
//			 return consultant.getSkills().stream()
//			            .filter(skill -> skills.contains(skill))
//			            .count();
//		}
		
		public List<Consultants> getBeachedConsultants(){
			return consultantsRepo.findConsultantsWithLatestNonOngoingPlacement();
		}
		
		

}
	
	
	
	
	

