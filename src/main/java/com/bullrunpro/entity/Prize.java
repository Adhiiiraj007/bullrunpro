package com.bullrunpro.entity;

import jakarta.persistence.*;

@Entity
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prizeName;
    private String prizeAmount;

    @Lob
    private byte[] image;

    private String imageType;

    private boolean published = false;

    @Column(length = 1000)
    private String note;

    // Getters & Setters

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public void setPrizeAmount(String prizeAmount) {
    }

    public void setImage(byte[] bytes) {
    }

    public void setImageType(String contentType) {
    }

    public void setNote(String note) {
    }

    public void setPublished(boolean b) {
    }
}
