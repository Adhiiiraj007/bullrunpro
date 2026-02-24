package com.bullrunpro.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "racer")
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated registration number (Unique)
    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String racerName;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String village;

    private String paymentId;
    private String orderId;
    private String signature;

    private Integer groupNumber;

    @Column(nullable = false)
    private boolean groupsPublished = false;

    @Column(nullable = false)
    private boolean withdrawn = false;

    // ✅ Used for "Today's Registrations"
    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate;

    // ✅ Used for sorting (latest first / oldest first)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ================= AUTO SET BEFORE INSERT =================
    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDate.now();
        this.createdAt = LocalDateTime.now();

        if (this.groupsPublished == false) {
            this.groupsPublished = false;
        }

        if (this.withdrawn == false) {
            this.withdrawn = false;
        }
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public boolean isGroupsPublished() {
        return groupsPublished;
    }

    public void setGroupsPublished(boolean groupsPublished) {
        this.groupsPublished = groupsPublished;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}