package com.example.touristguide;

import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.repository.TouristRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristRepositoryTest {
    private TouristRepository touristRepository;
    @BeforeEach
    void setUp() {
        touristRepository = new TouristRepository();
    }

    @Test
    void testAddTouristAttraction() {
        // Arrange
        TouristAttraction attraction = new TouristAttraction("The Acropolis,", "Perched above present day Athens, the Acropolis draws you up and in. Follow in the footsteps of the ancients as you walk up the same steps that have been walked on since 438 BC - 2,500 years.", "Athens", List.of("Test"));
        // Act
        List<TouristAttraction> result = touristRepository.addAttraction(attraction);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains(attraction));
    }

    @Test
    void testDeleteTouristAttraction() {
        // Arrange
        String name = "Pyramids of Giza";
        TouristAttraction attractionToRemove = new TouristAttraction(name, "If you've visited places like the Colosseum in Rome or the Acropolis in Athens, built over 2,000 years ago, you may think you have a good handle on ancient sites. But the Pyramids of Giza take ancient to a whole other level.", "Cairo", List.of("Test"));
        touristRepository.addAttraction(attractionToRemove);
        // Act
        List<TouristAttraction> result = touristRepository.deleteAttraction(name);
        // Assert
        assertNotNull(result);
        assertFalse(result.contains(attractionToRemove));
    }

    @Test
    void testUpdateAttraction() {
        // Arrange
        String name = "Great Wall of China";
        TouristAttraction existingAttraction = new TouristAttraction(name, "In a land of modern cities and towering skyscrapers, the Great Wall of China, built between the 14th and 17th centuries, is a stark contrast but a striking image that all visitors to China should see.", "Beijing", List.of("Test"));
        TouristAttraction updatedAttraction = new TouristAttraction(name, "Updated description", "Updated City", List.of("Updated"));
        touristRepository.addAttraction(existingAttraction);
        // Act
        TouristAttraction result = touristRepository.updateAttraction(name, updatedAttraction);
        // Assert
        assertNotNull(result);
        assertEquals(updatedAttraction.getDescription(), result.getDescription());
        assertEquals(updatedAttraction.getCity(), result.getCity());
        assertEquals(updatedAttraction.getTags(), result.getTags());
    }
}