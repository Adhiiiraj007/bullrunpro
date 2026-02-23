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

        List<Racer> racers = racerRepository.findByWithdrawnFalse();

        Collections.shuffle(racers);

        // Reset old groups + unpublished
        for (Racer r : racers) {
            r.setGroupNumber(null);
            r.setGroupsPublished(false);
        }

        int index = 0;
        int groupNumber = 1;

        for (Racer r : racers) {
            r.setGroupNumber(groupNumber);
            index++;

            if (index % groupSize == 0) {
                groupNumber++;
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

        List<Racer> published =
                racerRepository.findByGroupsPublishedTrue();

        if (published.isEmpty()) {
            model.addAttribute("notPublished", true);
        } else {
            model.addAttribute("racers", published);
        }

        return "public-groups";
    }
}