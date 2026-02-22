package com.bullrunpro.controller;

import com.bullrunpro.entity.Racer;
import com.bullrunpro.repository.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RacerRepository racerRepository;

    // ================= ADMIN LOGIN =================

    @GetMapping("/admin-portal")
    public String loginPage() {
        return "admin-login";
    }

    // ================= DASHBOARD =================

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalRacers",
                racerRepository.findByWithdrawnFalse().size());
        return "admin-dashboard";
    }

    // ================= VIEW ALL RACERS =================

    @GetMapping("/admin/racers")
    public String viewAllRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "admin-racers";
    }

    // ================= VIEW GROUPS (ADMIN) =================

    @GetMapping("/admin/groups")
    public String viewGroups(Model model) {
        model.addAttribute("racers",
                racerRepository.findByGroupNumberIsNotNull());
        return "admin-groups";
    }

    // ================= GENERATE GROUPS =================

    @PostMapping("/admin/generate-groups")
    public String generateGroups(@RequestParam int groupSize) {

        if (groupSize < 2) {
            return "redirect:/admin/groups";
        }

        // Only active racers
        List<Racer> racers = racerRepository.findByWithdrawnFalse();

        // Shuffle for fairness
        Collections.shuffle(racers);

        // Clear old groups + reset publish
        for (Racer r : racers) {
            r.setGroupNumber(null);
            r.setGroupsPublished(false);
        }

        int total = racers.size();
        int fullGroups = total / groupSize;
        int remaining = total % groupSize;

        int index = 0;
        int groupNumber = 1;

        // Create full groups
        for (int i = 0; i < fullGroups; i++) {
            for (int j = 0; j < groupSize; j++) {
                racers.get(index).setGroupNumber(groupNumber);
                index++;
            }
            groupNumber++;
        }

        // Handle remaining participants
        if (remaining > 0) {

            int half = remaining / 2;

            for (int i = 0; i < half; i++) {
                racers.get(index).setGroupNumber(groupNumber);
                index++;
            }

            groupNumber++;

            for (int i = 0; i < remaining - half; i++) {
                racers.get(index).setGroupNumber(groupNumber);
                index++;
            }
        }

        racerRepository.saveAll(racers);

        return "redirect:/admin/groups";
    }

    // ================= PUBLISH GROUPS =================

    @PostMapping("/admin/publish-groups")
    public String publishGroups() {

        List<Racer> racers =
                racerRepository.findByGroupNumberIsNotNull();

        for (Racer r : racers) {
            r.setGroupsPublished(true);
        }

        racerRepository.saveAll(racers);

        return "redirect:/admin/groups";
    }

    // ================= PUBLIC VIEW =================

    @GetMapping("/groups")
    public String viewGroupsPublic(Model model) {

        model.addAttribute("racers",
                racerRepository.findByGroupsPublishedTrue());

        return "public-groups";
    }
}