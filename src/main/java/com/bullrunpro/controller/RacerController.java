package com.bullrunpro.controller;

import com.bullrunpro.entity.Racer;
import com.bullrunpro.repository.RacerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RacerController {

    private final RacerRepository racerRepository;

    public RacerController(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Show Registration Form
    @GetMapping("/registration")
    public String showForm(Model model) {
        model.addAttribute("racer", new Racer());
        return "registration";
    }

    // Save Racer
    @PostMapping("/saveRacer")
    public String saveRacer(@ModelAttribute Racer racer) {
        racerRepository.save(racer);
        return "success";
    }

    // View All Racers
    @GetMapping("/racers")
    public String viewRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }
}
