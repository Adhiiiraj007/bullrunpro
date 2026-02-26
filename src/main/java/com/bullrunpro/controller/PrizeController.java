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


    // ADMIN PAGE
    @GetMapping("/admin/prize-chart")
    public String adminPrizePage(Model model) {
        model.addAttribute("prizes", prizeRepository.findAll());
        return "admin-prize-chart";
    }

    // ADD PRIZE
    @PostMapping("/admin/prize-chart")
    public String savePrize(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        Prize prize = new Prize();
        prize.setTitle(title);
        prize.setDescription(description);

        if (!image.isEmpty()) {
            prize.setImage(image.getBytes());
        }

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

    @PostMapping("/admin/add-prize")
    public String addPrize(
            @RequestParam("prizeName") String prizeName,
            @RequestParam("prizeAmount") String prizeAmount,
            @RequestParam("note") String note,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        Prize prize = new Prize();

        prize.setPrizeName(prizeName);
        prize.setPrizeAmount(prizeAmount);
        prize.setNote(note);

        if (!image.isEmpty()) {
            prize.setImage(image.getBytes());
            prize.setImageType(image.getContentType());
        }

        prize.setPublished(false);

        prizeRepository.save(prize);

        return "redirect:/admin/prize-chart";
    }

    @GetMapping("/user/prize-chart")
    public String userPrizeChart(Model model) {

        List<Prize> publishedPrizes =
                prizeRepository.findByPublishedTrue();

        model.addAttribute("prizes", publishedPrizes);

        return "user-prize-chart";
    }
}
