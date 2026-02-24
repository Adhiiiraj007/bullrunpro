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
    public String saveRacer(@ModelAttribute Racer racer) {

        try {

            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", racer.getOrderId());
            options.put("razorpay_payment_id", racer.getPaymentId());
            options.put("razorpay_signature", racer.getSignature());

            boolean isValid = Utils.verifyPaymentSignature(options, keySecret);

            if (!isValid) {
                return "redirect:/registration?error=payment_failed";
            }

            racerRepository.save(racer);

            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/registration?error=server_error";
        }
    }

    // View Racers
    @GetMapping("/racers")
    public String viewRacers(Model model) {
        model.addAttribute("racers", racerRepository.findAll());
        return "racers";
    }
}