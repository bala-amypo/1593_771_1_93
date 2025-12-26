package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InteractionResponseDTO {
    private Long id;
    private String medications;
    private List<InteractionDto> interactions;
    private LocalDateTime checkedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    public List<InteractionDto> getInteractions() { return interactions; }
    public void setInteractions(List<InteractionDto> interactions) { this.interactions = interactions; }
    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }
}