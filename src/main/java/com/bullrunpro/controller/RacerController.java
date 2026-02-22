package com.bullrunpro.controller;

import com.bullrunpro.entity.Racer;
import com.bullrunpro.repository.RacerRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class RacerController {

    private final RacerRepository racerRepository;

    public RacerController(RacerRepository racerRepository) {
        this.racerRepository = racerRepository;
    }

    // Razorpay Keys from application.properties
    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Registration Form
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

    // View Racers
    @GetMapping("/racers")
    public String viewRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }

    // Create Razorpay Order
    @PostMapping("/create-order")
    @ResponseBody
    public String createOrder() throws Exception {

        RazorpayClient client =
                new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();

        options.put("amount", 1500);
        options.put("currency", "INR");
        options.put("receipt", "bullrunpro_receipt");

        Order order = client.orders.create(options);

        return order.toString();
    }


}
