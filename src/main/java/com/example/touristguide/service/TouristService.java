package com.example.touristguide.service;

import org.springframework.stereotype.Service;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;

import java.util.List;

@Service
public class TouristService {

    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAttractionList() {
        return touristRepository.getAttractionList();
    }

    public List<String> attractionTags(String touristAttractionName) {
        return touristRepository.tags(touristAttractionName);
    }

    public List<TouristAttraction> addAttraction(TouristAttraction attraction) {
        return touristRepository.addTouristAttraction(attraction);
    }

    public List<String> getAttractionCities() {
        return touristRepository.getCities();
    }

    public List<String> getTags() {
        return touristRepository.getTags();
    }

    public List<TouristAttraction> deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction touristAttraction) {
        return touristRepository.updateAttraction(name, touristAttraction);
    }

    public TouristAttraction getAttraction(String name) {
        return touristRepository.getAttraction(name);
    }
}
