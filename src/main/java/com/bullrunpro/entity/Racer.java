package com.bullrunpro.entity;

import jakarta.persistence.*;

@Entity
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String racerName;
    private String phoneNumber;
    private String village;

    // Razorpay Details
    private String paymentId;
    private String orderId;
    private String signature;

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRacerName() { return racerName; }
    public void setRacerName(String racerName) { this.racerName = racerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}