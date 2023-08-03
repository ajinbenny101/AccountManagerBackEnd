package com.fdmgroup.springauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public Placements getPlacementById(int id) {
        Optional<Placements> optionalPlacement = placementsRepo.findById(id);

        if (optionalPlacement.isPresent()) {
            return optionalPlacement.get();
        } else {
            return null;
        }
    }

    public Placements addPlacement(Placements placement) {
        return placementsRepo.save(placement);
    }

    public Placements updatePlacement(Placements placement) {
        if (placementsRepo.existsById(placement.getId())) {
            return placementsRepo.save(placement);
        } else {
            return null;
        }
    }

    public void deletePlacementById(int id) {
        if (placementsRepo.existsById(id)) {
            placementsRepo.deleteById(id);
        }
    }
}
