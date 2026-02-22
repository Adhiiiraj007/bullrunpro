package com.bullrunpro.entity;

import jakarta.persistence.*;

@Entity
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String registrationNumber;

    private String racerName;
    private String phoneNumber;
    private String village;

    private boolean withdrawn = false;

    // ✅ NEW: Group Number
    private Integer groupNumber;

    // ✅ NEW: Group publish control (admin controlled)
    private boolean groupsPublished = false;

    public Racer() {}

    // Auto-generate Registration Number
    @PrePersist
    public void generateRegistrationNumber() {
        if (this.registrationNumber == null) {
            this.registrationNumber = "BRP-" + System.currentTimeMillis();
        }
    }

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() { return id; }

    public String getRegistrationNumber() { return registrationNumber; }

    public String getRacerName() { return racerName; }
    public void setRacerName(String racerName) { this.racerName = racerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public boolean isWithdrawn() { return withdrawn; }
    public void setWithdrawn(boolean withdrawn) { this.withdrawn = withdrawn; }

    // ✅ Group Number
    public Integer getGroupNumber() { return groupNumber; }
    public void setGroupNumber(Integer groupNumber) { this.groupNumber = groupNumber; }

    // ✅ Publish Control
    public boolean isGroupsPublished() { return groupsPublished; }
    public void setGroupsPublished(boolean groupsPublished) {
        this.groupsPublished = groupsPublished;
    }
}