package com.bullrunpro.entity;

import jakarta.persistence.*;

@Entity
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto Registration Number
    @Column(unique = true)
    private String regNo;

    private String racerName;
    private String phoneNumber;
    private String village;

    // Payment Details
    private String paymentId;
    private String orderId;
    private String signature;

    // Group Management
    private Integer groupNumber;

    private Boolean groupsPublished = false;

    private Boolean withdrawn = false;

    // ================= GETTERS SETTERS =================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

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

    public Boolean getGroupsPublished() { return groupsPublished; }
    public void setGroupsPublished(Boolean groupsPublished) { this.groupsPublished = groupsPublished; }

    public Boolean getWithdrawn() { return withdrawn; }
    public void setWithdrawn(Boolean withdrawn) { this.withdrawn = withdrawn; }
}