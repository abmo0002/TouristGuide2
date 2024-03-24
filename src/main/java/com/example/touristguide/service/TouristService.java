package com.example.touristguide.service;

import org.springframework.stereotype.Service;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;
import com.example.touristguide.repository.TouristRepositoryDB;

import java.util.List;

@Service
public class TouristService {

    private TouristRepository touristRepository;
    private TouristRepositoryDB touristRepositoryDB;

    public TouristService(TouristRepository touristRepository, TouristRepositoryDB touristRepositoryDB){
        this.touristRepository = touristRepository;
        this.touristRepositoryDB = touristRepositoryDB;
    }


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
        return touristRepository.addAttraction(attraction);
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
    public List<TouristAttraction> getAttraction(){
        return touristRepositoryDB.getAllAttractions();
    }
    public List<String> getAttractionTags(String name){
        return touristRepositoryDB.Tags(name);
    }

    public void deleteAttractionDB(String name){
        touristRepositoryDB.deleteAttraction(name);
    }

    public void addAttractionDB(TouristAttraction touristAttraction){
        touristRepositoryDB.addAttraction(touristAttraction);
    }

    public List<String> getTagsDB(){
        return touristRepositoryDB.getTags();
    }

    public List<String> getCitiesDB(){
        return touristRepositoryDB.getCities();
    }

}
