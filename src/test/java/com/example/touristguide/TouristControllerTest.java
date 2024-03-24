package com.example.touristguide;

import com.example.touristguide.controller.TouristController;
import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TouristControllerTest {

    @Mock
    private TouristService touristService;

    @InjectMocks
    private TouristController touristController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAttractions() {
        Model model = mock(Model.class);
        List<TouristAttraction> attractions = Collections.singletonList(new TouristAttraction());
        when(touristService.getAttractionList()).thenReturn(attractions);

        String viewName = touristController.getAllAttractions(model);

        assertEquals("attractionList", viewName);
        verify(model).addAttribute("attractions", attractions);
    }

    @Test
    public void testTags() {
        Model model = mock(Model.class);
        List<String> tags = Collections.singletonList("tag");
        when(touristService.attractionTags("TestName")).thenReturn(tags);

        String viewName = touristController.tags("TestName", model);

        assertEquals("tags", viewName);
        verify(model).addAttribute("tags", tags);
    }

    @Test
    public void testAddAttraction() {
        Model model = mock(Model.class);
        TouristAttraction attraction = new TouristAttraction();
        when(touristService.getAttractionCities()).thenReturn(Collections.singletonList("City"));
        when(touristService.getTags()).thenReturn(Collections.singletonList("Tag"));

        String viewName = touristController.addAttraction(model);

        assertEquals("add", viewName);
        verify(model).addAttribute("attractionAdd", attraction);
        verify(model).addAttribute("attractionCities", Collections.singletonList("City"));
        verify(model).addAttribute("attractionTags", Collections.singletonList("Tag"));
    }

    @Test
    public void testAddedAttraction() {
        TouristAttraction attraction = new TouristAttraction();

        String viewName = touristController.addedAttraction(attraction);

        assertEquals("redirect:/attractions", viewName);
        verify(touristService).addAttraction(attraction);
    }


}