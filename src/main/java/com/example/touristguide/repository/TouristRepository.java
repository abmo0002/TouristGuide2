package com.example.touristguide.repository;

import com.example.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private List<TouristAttraction> attractionList = new ArrayList<>
            (List.of(
                    new TouristAttraction("Eiffel Tower", "Completed for the 1889 Exposition Universelle after two years of construction, it has 1,665 steps to the top and elevators to two observation levels.", "Paris", List.of("International symbol")),
                    new TouristAttraction("Hollywood Sign", "The Hollywood sign, which debuted as Hollywoodland in 1923 to advertise a real estate development, was rebuilt in 1978 to mark the iconic industry that makes Los Angeles a company town, even today.", "Los Angeles", List.of("Landmark", "Cultural icon")),
                    new TouristAttraction("Burj Khalifa", "While all United Arab Emirates countries boast architectural superlatives, the tallest of the futuristic towers – and the tallest building in the world – is Burj Khalifa, whose 160 stories are housed within a slender, silver and glass, 2,716.5-foot-tall frame.", "Dubai", List.of("Architecture")),
                    new TouristAttraction("Times Square", "With 50 million visitors annually, Manhattan's monument to billboards, Broadway and buskers is a must.", "New York", List.of("Entertainment")),
                    new TouristAttraction("Buckingham Palace", "Buckingham Palace, the royal residence since 1837, only opened for public visits in 1993. Greater as a symbol of power than as a mansion, it nonetheless fronts 354 feet of prime real estate between Green and St. James parks. The palace has 775 rooms, including 52 royal and guest bedrooms, 78 bathrooms and 188 bedrooms for staff. ","London", List.of("Landmark")),
                    new TouristAttraction("Tivoli ", "Tivoli Gardens, also known simply as Tivoli, is an amusement park and pleasure garden in Copenhagen, Denmark. The park opened on 15 August 1843 and is the third-oldest operating amusement park in the world, after Dyrehavsbakken in nearby Klampenborg, also in Denmark, and Wurstelprater in Vienna, Austria.", "Copenhagen", List.of("Amusement park")),
                    new TouristAttraction("Colosseum", "Now the remains of an enormous, carved marble ellipse, the Colosseum was commissioned in A.D. 72 by Emperor Vespasian as an amphitheater to entertain the masses. The structure features four levels pierced by 80 arched entrances, which allowed 55,000 spectators to quickly take seats to watch all-day games between wild animals, slaves and criminals..","Rome", List.of("Amphitheatre"))));
    final private List<String> Cities = new ArrayList<>
            (List.of("Paris", "Los Angeles", "Dubai", "New York", "London", "Rome", "Copenhagen"));

    final private List<String> tags = new ArrayList<>
            (List.of("International symbol", "Landmark", "Cultural icon", "Architecture", "Entertainment", "Amphitheatre", "Amusement park"));


    public List<TouristAttraction> getAttractionList() {
        return attractionList;
    }

    public List<String> getCities() {
        return Cities;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> tags(String touristAttractionName) {
        for (TouristAttraction touristAttraction : attractionList) {
            if (touristAttraction.getName().contains(touristAttractionName)) {
                return touristAttraction.getTags();
            }
        }
        return null;
    }

    public List<TouristAttraction> addTouristAttraction(TouristAttraction touristAttraction) {
        attractionList.add(touristAttraction);
        return attractionList;
    }

    public List<TouristAttraction> deleteAttraction(String name) {
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getName().contains(name)) {
                attractionList.remove(attraction);
                return attractionList;
            }
        }
        return null;
    }

    public TouristAttraction updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction : attractionList) {
            if (attraction.getName().contains(name)) {
                attraction.setDescription(updatedAttraction.getDescription());
                attraction.setCity(updatedAttraction.getCity());
                attraction.setTags(updatedAttraction.getTags());

                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction getAttraction(String name) {
        for (TouristAttraction touristAttraction : attractionList) {
            if (touristAttraction.getName().contains(name)) {
                return touristAttraction;
            }
        }
        return null;
    }


}



