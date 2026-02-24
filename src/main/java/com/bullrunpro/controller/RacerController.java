package com.bullrunpro.controller;

import com.bullrunpro.entity.Racer;
import com.bullrunpro.repository.RacerRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RacerController {

    @Autowired
    private RacerRepository racerRepository;

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    // Home
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Registration Page
    @GetMapping("/registration")
    public String showForm(Model model) {
        model.addAttribute("racer", new Racer());
        return "registration";
    }

    // Create Razorpay Order
    @PostMapping("/create-order")
    @ResponseBody
    public String createOrder() throws Exception {

        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", 1500); // ₹15 (amount in paisa)
        options.put("currency", "INR");
        options.put("receipt", "bullrunpro_receipt");

        return client.orders.create(options).toString();
    }

    // Save Racer After Payment Verification
    @PostMapping("/saveRacer")
    public String saveRacer(@ModelAttribute Racer racer){

        // Auto generate registration number
        racer.setRegistrationNumber("BRP-" + System.currentTimeMillis());

        racerRepository.save(racer);

        return "success";
    }

    // View Racers
    @GetMapping("/racers")
    public String viewRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }

    // Webhook Controller

    @PostMapping("/razorpay/webhook")
    @ResponseBody
    public void webhookHandler(@RequestBody String payload) {

        System.out.println("Webhook received");

        // You can verify signature here later
    }
}