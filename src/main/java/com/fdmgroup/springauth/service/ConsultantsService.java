package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.exceptions.ConsultantExistsException;
import com.fdmgroup.springauth.exceptions.ConsultantNotFoundException;
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

	public Consultants getConsultantById(int id) throws ConsultantNotFoundException  {
		Optional<Consultants> optionalConsultant = consultantsRepo.findById(id);

		if (optionalConsultant.isPresent()) {
			return optionalConsultant.get();
		}
		 else {
			 throw new ConsultantNotFoundException("No Consultant with id of " + id);
		    }
	}

	
	
	public Consultants addConsultant(Consultants consultant) throws ConsultantExistsException {
        Optional<Consultants> optionalConsultant = consultantsRepo.findByFirstNameAndLastNameAndFdmEmail(
                consultant.getFirstName(), consultant.getLastName(), consultant.getFdmEmail());

        if (optionalConsultant.isPresent()) {
            throw new ConsultantExistsException(
                    "Consultant " + consultant.getFirstName() + " " + consultant.getLastName() + " " + consultant.getFdmEmail() + " already exists");
        }

        return consultantsRepo.save(consultant);
    }

	public Consultants updateConsultant(Consultants consultant) throws ConsultantNotFoundException {
		if (consultantsRepo.existsById(consultant.getId())) {
			return consultantsRepo.save(consultant);
		} else {
			throw new ConsultantNotFoundException("No Consultant with id  " + consultant.getId() + "too update");
	    }
	}

	public void deleteConsultantById(int id) throws ConsultantNotFoundException {
		if (consultantsRepo.existsById(id)) {
			consultantsRepo.deleteById(id);
		} 
		else {
			throw new ConsultantNotFoundException("No Consultant with id  " + id + " too delete");
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
	
	

}
	
	
	
	
	

