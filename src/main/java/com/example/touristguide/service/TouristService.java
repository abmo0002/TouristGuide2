package com.example.touristguide.service;

import com.example.touristguide.modul.TouristAttractions;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public void addAttraction(TouristAttractions attraction) {
        touristRepository.addAttraction(attraction);
    }

    public List<TouristAttractions> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttractions getAttraction(String name) {
        return touristRepository.getAttraction(name);
    }

    public void updateAttraction(String name, TouristAttractions updatedAttraction) {
        touristRepository.updateAttraction(name, updatedAttraction);
    }

    public void deleteAttraction(String name) {
        touristRepository.deleteAttraction(name);
    }
}
