package com.fdmgroup.springauth.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.exceptions.ExistsException;
import com.fdmgroup.springauth.exceptions.NotFoundException;
import com.fdmgroup.springauth.model.JobField;
import com.fdmgroup.springauth.model.Placements;
import com.fdmgroup.springauth.repository.PlacementsRepository;
@Service
public class PlacementsService {

    private final PlacementsRepository placementsRepo;

    public PlacementsService(PlacementsRepository placementsRepo) {
        this.placementsRepo = placementsRepo;
    }

    public List<Placements> getAllPlacements() {
        return placementsRepo.findAll();
    }

    public Placements getPlacementById(int id)throws NotFoundException {
        Optional<Placements> optionalPlacement = placementsRepo.findById(id);

        if (optionalPlacement.isPresent()) {
            return optionalPlacement.get();
        } else {
        	throw new NotFoundException("No Placement with id of " + id);
	    }
    }

    public Placements addPlacement(Placements placement) throws ExistsException{
    	 if (placementsRepo.existsByNameOfCompanyAndJobTitleAndStartDateAndEndDateAndLocation(
                 placement.getNameOfCompany(),
                 placement.getJobTitle(),
                 placement.getStartDate(),
                 placement.getEndDate(),
                 //placement.getExpectedEndDate(),
                 //placement.getPostedOn(),
                 //placement.getPlacementFilledOn(),
                 placement.getLocation())) {
             throw new ExistsException("Placement with the same information already exists.");
         }
     
    	 else {
        return placementsRepo.save(placement);
        }
    }

    public Placements updatePlacement(Placements placement) throws NotFoundException{
        if (placementsRepo.existsById(placement.getId())) {
            return placementsRepo.save(placement);
        } else {
        	throw new NotFoundException("No Placement with id  " + placement.getId() + "too update");
        }
    }

    public void deletePlacementById(int id) throws NotFoundException{
        if (placementsRepo.existsById(id)) {
            placementsRepo.deleteById(id);
            }
        else {
    			throw new NotFoundException("No Placement with id  " + id + " too delete");
    		}
    }
    
    public List<Placements> getUpcomingPlacements() {
        Date currentDate = new Date(); 
        return placementsRepo.findByStartDateAfter(currentDate);
    }

    public List<Placements> getNotFilledPlacements() {
        return placementsRepo.findByPlacementFilledOnIsNull();
    }
    public List<Placements> getPlacementsByJobField(JobField jobField) {
        return placementsRepo.findByJobField(jobField);
    }

}
