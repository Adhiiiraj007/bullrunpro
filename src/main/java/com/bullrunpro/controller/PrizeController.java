package com.bullrunpro.controller;

import com.bullrunpro.entity.Prize;
import com.bullrunpro.repository.PrizeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class PrizeController {

    private final PrizeRepository prizeRepository;

    public PrizeController(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    // USER VIEW
    @GetMapping("/prize-chart")
    public String viewPrizeChart(Model model) {
        model.addAttribute("prizes", prizeRepository.findByPublishedTrue());
        return "user-prize-chart";
    }

    // ADMIN PAGE
    @GetMapping("/admin/prize-chart")
    public String adminPrizePage(Model model) {
        model.addAttribute("prizes", prizeRepository.findAll());
        return "admin-prize-chart";
    }

    // ADD PRIZE
    @PostMapping("/admin/add-prize")
    public String addPrize(@RequestParam String prizeName,
                           @RequestParam String prizeAmount,
                           @RequestParam MultipartFile image,
                           @RequestParam String note) throws IOException {

        Prize prize = new Prize();
        prize.setPrizeName(prizeName);
        prize.setPrizeAmount(prizeAmount);
        prize.setImage(image.getBytes());
        prize.setImageType(image.getContentType());
        prize.setNote(note);

        prizeRepository.save(prize);

        return "redirect:/admin/prize-chart";
    }

    // PUBLISH
    @PostMapping("/admin/publish-prizes")
    public String publishPrizes() {
        List<Prize> prizes = prizeRepository.findAll();
        prizes.forEach(p -> p.setPublished(true));
        prizeRepository.saveAll(prizes);
        return "redirect:/admin/prize-chart";
    }

    // UNPUBLISH
    @PostMapping("/admin/unpublish-prizes")
    public String unpublishPrizes() {
        List<Prize> prizes = prizeRepository.findAll();
        prizes.forEach(p -> p.setPublished(false));
        prizeRepository.saveAll(prizes);
        return "redirect:/admin/prize-chart";
    }
}
