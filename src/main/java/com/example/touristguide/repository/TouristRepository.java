package com.example.touristguide.repository;
import com.example.touristguide.modul.TouristAttractions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private final List<TouristAttractions> attractions;

    public TouristRepository() {
        this.attractions = new ArrayList<>();
        attractions.add(new TouristAttractions("Buckingham Palace", "The official residence of the British monarch in London, Buckingham Palace is an iconic symbol of the British monarchy."));
        attractions.add(new TouristAttractions("Big Ben", "An iconic landmark in London, Big Ben is a notable clock tower celebrated for its grandeur and historical significance."));
        attractions.add(new TouristAttractions("The British Museum", "The British Museum, situated in London, is a renowned cultural institution boasting an extensive collection of ancient artifacts and artworks."));
        attractions.add(new TouristAttractions("Windsor Castle", "Windsor Castle, a venerable fortress in England, holds the distinction of being the largest and oldest continually inhabited castle worldwide."));
    }

    public void addAttraction(TouristAttractions attraction) {
        attractions.add(attraction);
    }

    public List<TouristAttractions> getAllAttractions() {
        return attractions;
    }

    public TouristAttractions getAttraction(String name) {
        for (TouristAttractions attraction : attractions) {
            if (attraction.getName().equals(name)) {
                return attraction;
            }
        }
        return null;
    }

    public void updateAttraction(String name, TouristAttractions updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equals(name)) {
                attractions.set(i, updatedAttraction);
                return;
            }
        }
    }

    public void deleteAttraction(String name) {
        attractions.removeIf(attraction -> attraction.getName().equals(name));
    }

    public void addTag(String attractionName, String tag) {
        for (TouristAttractions attraction : attractions) {
            if (attraction.getName().equals(attractionName)) {

                return;
            }
        }
    }

    public void saveAttraction(TouristAttractions newAttraction) {
        if (!attractions.contains(newAttraction)) {
            attractions.add(newAttraction);
        }
    }

    public void editAttraction(String name, TouristAttractions updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equals(name)) {
                attractions.set(i, updatedAttraction);
                return;
            }
        }
    }
}

