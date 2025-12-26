package com.example.demo.dto;

import java.util.List;

public class InteractionCheckRequestDTO {
    private List<Long> medicationIds;

    public List<Long> getMedicationIds() { return medicationIds; }
    public void setMedicationIds(List<Long> medicationIds) { this.medicationIds = medicationIds; }
}