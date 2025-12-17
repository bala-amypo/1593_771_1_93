package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InteractionCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medications;
    private String interactions;

    private LocalDateTime checkedAt;

    @PrePersist
    public void assignTime() {
        checkedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getMedications() { return medications; }
    public String getInteractions() { return interactions; }
    public LocalDateTime getCheckedAt() { return checkedAt; }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }
}
