package com.example.touristguide.controller;

import com.example.touristguide.model.TouristAttraction;
import com.example.touristguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("attractions")
public class TouristController {
    private TouristService touristService;

    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping("")
    public String getAllAttractions(Model model){
        model.addAttribute("attractions", touristService.getAttraction());
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String tags(@PathVariable("name") String name, Model model){
        model.addAttribute("tags", touristService.getAttractionTags(name));
        return "attractionTags";
    }

    @GetMapping("/add")
    public String addAttraction(Model model){
        model.addAttribute("attractionObject", new TouristAttraction());
        model.addAttribute("attractionCities", touristService.getCitiesDB());
        model.addAttribute("attractionTags", touristService.getTagsDB());
        return "addAttraction";
    }

    @PostMapping("/add")
    public String addedAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.addAttractionDB(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable("name") String name){
        touristService.deleteAttractionDB(name);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/updateAttraction")
    public String updateAttration(@PathVariable("name") String name, Model model){
        TouristAttraction touristAttraction = touristService.getAttraction(name);

        model.addAttribute("updateObject", touristService.getAttraction(name));
        model.addAttribute("attractionTags", touristService.getTags());
        return "updateAttraction";
    }

    @PostMapping("/updateAttraction")
    public String updatedAttraction(@ModelAttribute TouristAttraction updatedTouristAttraction){
        touristService.updateAttraction(updatedTouristAttraction.getName(), updatedTouristAttraction);
        return "redirect:/attractions";
    }
}






