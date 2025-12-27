package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_result")
public class InteractionCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String medications; // comma-separated medication names

    @Column(columnDefinition = "TEXT")
    private String interactions; // JSON summary

    private LocalDateTime checkedAt;

    @PrePersist
    public void onCreate() {
        this.checkedAt = LocalDateTime.now();
    }

    public InteractionCheckResult() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMedications() { return medications; }
    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getInteractions() { return interactions; }
    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
