package com.bullrunpro.controller;

import com.bullrunpro.entity.Racer;
import com.bullrunpro.repository.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private RacerRepository racerRepository;

    // ================= LOGIN =================
    @GetMapping("/admin-portal")
    public String loginPage() {
        return "admin-login";
    }

    // ================= DASHBOARD =================
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalRacers",
                racerRepository.findByWithdrawnFalse().size());

        model.addAttribute("groupsCreated",
                racerRepository.findByGroupNumberIsNotNull().size());

        model.addAttribute("groupsPublished",
                racerRepository.findByGroupsPublishedTrue().size() > 0);

        return "admin-dashboard";
    }

    // ================= VIEW RACERS =================
    @GetMapping("/admin/racers")
    public String viewAllRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "admin-racers";
    }

    // ================= VIEW GROUPS =================
    @GetMapping("/admin/groups")
    public String viewGroups(Model model) {

        List<Racer> racers =
                racerRepository.findByGroupNumberIsNotNull();

        if (racers.isEmpty()) {
            model.addAttribute("noGroups", true);
            return "admin-groups";
        }

        // Sort by group number
        racers.sort((r1, r2) ->
                r1.getGroupNumber().compareTo(r2.getGroupNumber()));

        // Group by group number
        Map<Integer, List<Racer>> groupedRacers =
                racers.stream()
                        .collect(Collectors.groupingBy(Racer::getGroupNumber));

        model.addAttribute("groupedRacers", groupedRacers);

        model.addAttribute("published",
                racerRepository.findByGroupsPublishedTrue().size() > 0);

        return "admin-groups";
    }

    // ================= GENERATE GROUPS =================
    @PostMapping("/admin/generate-groups")
    public String generateGroups(@RequestParam int groupSize) {

        if (groupSize < 2) {
            return "redirect:/admin/groups";
        }

        List<Racer> racers = racerRepository.findByWithdrawnFalse();

        if (racers.isEmpty()) {
            return "redirect:/admin/groups";
        }

        // Randomize list
        Collections.shuffle(racers);

        int groupNumber = 1;

        for (int i = 0; i < racers.size(); i++) {

            Racer racer = racers.get(i);

            racer.setGroupNumber((i / groupSize) + 1);
            racer.setGroupsPublished(false);
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

    // ================= UNPUBLISH GROUPS =================
    @PostMapping("/admin/unpublish-groups")
    public String unpublishGroups() {

        List<Racer> racers =
                racerRepository.findByGroupNumberIsNotNull();

        for (Racer r : racers) {
            r.setGroupsPublished(false);
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
            return "public-groups";
        }

        // Group racers by group number
        Map<Integer, List<Racer>> groupedRacers =
                published.stream()
                        .collect(Collectors.groupingBy(Racer::getGroupNumber));

        model.addAttribute("groupedRacers", groupedRacers);

        return "public-groups";
    }


    // ================= DELETE GROUPS =================

    @PostMapping("/admin/delete-groups")
    public String deleteGroups() {

        List<Racer> racers =
                racerRepository.findByGroupNumberIsNotNull();

        for (Racer r : racers) {
            r.setGroupNumber(null);
            r.setGroupsPublished(false);
        }

        racerRepository.saveAll(racers);

        return "redirect:/admin/groups";
    }
}