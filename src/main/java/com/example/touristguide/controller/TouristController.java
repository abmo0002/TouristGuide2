package com.example.touristguide.controller;

import com.example.touristguide.modul.TouristAttractions;
import com.example.touristguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TouristController {

    private  TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("/attractions")
    public ResponseEntity<List<TouristAttractions>> getAllAttractions() {
        List<TouristAttractions> attractions = touristService.getAllAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("/attractions/{name}/tags")
    public ResponseEntity<?> getAttractionTags(@PathVariable String name) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/attractions/add")
    public ResponseEntity<?> addAttraction( @RequestBody TouristAttractions attractions) {
        touristService.addAttraction(attractions);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/attractions/save")
    public ResponseEntity<?> saveAttraction(@PathVariable String save) {
        
        return new ResponseEntity<>("Attraction saved", HttpStatus.OK);
    }

    @PostMapping("/attractions/update")
    public ResponseEntity<?> updateAttraction(@PathVariable String name, @RequestBody TouristAttractions updatedAttraction) {
        touristService.updateAttraction(name, updatedAttraction);
        return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);

    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return new ResponseEntity<>("Attraction deleted", HttpStatus.OK);
    }
}



















































