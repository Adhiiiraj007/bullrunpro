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
    private String cartNumber;

    public Racer() {}

    public Long getId() { return id; }

    public String getRacerName() { return racerName; }
    public void setRacerName(String racerName) { this.racerName = racerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getCartNumber() { return cartNumber; }
    public void setCartNumber(String cartNumber) { this.cartNumber = cartNumber; }
}
