package com.bullrunpro.entity;

import jakarta.persistence.*;

@Entity
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String registrationNumber;   // ✅ change here

    private String racerName;
    private String phoneNumber;
    private String village;

    private String paymentId;
    private String orderId;
    private String signature;

    private Integer groupNumber;

    private boolean groupsPublished = false;
    private boolean withdrawn = false;

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

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

    public Integer getGroupNumber() { return groupNumber; }
    public void setGroupNumber(Integer groupNumber) { this.groupNumber = groupNumber; }

    public boolean isGroupsPublished() { return groupsPublished; }
    public void setGroupsPublished(boolean groupsPublished) { this.groupsPublished = groupsPublished; }

    public boolean isWithdrawn() { return withdrawn; }
    public void setWithdrawn(boolean withdrawn) { this.withdrawn = withdrawn; }
}