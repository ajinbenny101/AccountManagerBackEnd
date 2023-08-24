package com.fdmgroup.springauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.dto.ConsultantsDto;
import com.fdmgroup.springauth.exceptions.ExistsException;
import com.fdmgroup.springauth.exceptions.NotFoundException;
import com.fdmgroup.springauth.model.Consultants;
import com.fdmgroup.springauth.model.Interests;
import com.fdmgroup.springauth.model.Placements;
import com.fdmgroup.springauth.model.Qualifications;
import com.fdmgroup.springauth.model.Skills;
import com.fdmgroup.springauth.model.Streams;
import com.fdmgroup.springauth.repository.ConsultantsRepository;
import com.fdmgroup.springauth.repository.PlacementsRepository;
import com.fdmgroup.springauth.repository.StreamsRepository;



@Service
public class ConsultantsService {
	private final ConsultantsRepository consultantsRepo;
	private final PlacementsRepository placementsRepo;
	private final StreamsRepository streamsRepo;
	
	public ConsultantsService(ConsultantsRepository consultantsRepo, PlacementsRepository placementsRepo, StreamsRepository streamsRepo) {
        this.consultantsRepo = consultantsRepo;
        this.placementsRepo = placementsRepo;
        this.streamsRepo = streamsRepo;
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
		
		public List<Consultants> getConsultantsforPlacement(int placementId){
			Placements placement = placementsRepo.findById(placementId).get();
			List<Skills> desiredSkills = placement.getSkills();
			
			return consultantsRepo.findBySkillsIn(desiredSkills);
		}
		
		public List<Consultants> getConsultantsByCriteria(ConsultantsDto consultantDto){
			List<Consultants> beachedConsultants = consultantsRepo.findConsultantsWithLatestNonOngoingPlacement();
			//Check getByGeoflexIdIn may not be right in JPA 
			List<Consultants> geoflexConsultants = consultantsRepo.findByGeoflexIdIn(consultantDto.getGeoflex());
			//Unlikely to work, how is it finding the qualifcation from the dto 
			List<String> qualificationType = new ArrayList<String>();
			for(Qualifications qualification : consultantDto.getQualifications()) {
				qualificationType.add(qualification.getQualificationType());
			}
			List<Consultants> qualifiedConsultants = consultantsRepo.findByQualificationsQualificationTypeIn(qualificationType);
			List<String> qualificationName = new ArrayList<String>();
			for(Qualifications qualification : consultantDto.getQualifications()) {
				qualificationType.add(qualification.getQualificationName());
			}
			List<Consultants> qualifiedTypeConsultants = consultantsRepo.findByQualificationsQualificationNameIn(qualificationName);
			//List<Consultants> qualifiedNameConsultants = 
			
			List<Consultants> skillsConsulants = consultantsRepo.findBySkillsIdIn(consultantDto.getSkills());
			List<Consultants> streamConsultants = consultantsRepo.findByStream(consultantDto.getStream());
			List<Consultants> interestsConsultants = consultantsRepo.findByInterestsInterestCodeIn(consultantDto.getInterests());
			//maybe get all the lists of things then merge into a set or other data structure so only take duplicates?????
			return null;
		}
		
		public List<Consultants> getConsultantsByQualifications(List<Qualifications> qualifications){
			List<Consultants> consultantsWithQualifications = consultantsRepo.findByQualificationsIn(qualifications);
			return consultantsWithQualifications;
		}
	 
	 	
	 	public List<Consultants> getBeachedConsultants(){
			return consultantsRepo.findConsultantsWithLatestNonOngoingPlacement();
		}
	 	
	 	public List<Consultants> getConsultantsByStream(String streamCode) {
	 		Streams stream = streamsRepo.findByStreamCode(streamCode).get();
	 		return consultantsRepo.findByStream(stream);
	 	}
		
		public List<Consultants> getConsultantsBySkills(List<Skills> skills) {
			List<Consultants> consultantsWithSkills = consultantsRepo.findBySkillsIn(skills);
			return consultantsWithSkills;
		}

		public List<Consultants> getConsultantsByInterests(List<String> interests) {
			List<Consultants> consultantsWithInterests = consultantsRepo.findByInterestsInterestCodeIn(interests);
			return consultantsWithInterests;
		}
		
		

}
	
	
	
	
	

