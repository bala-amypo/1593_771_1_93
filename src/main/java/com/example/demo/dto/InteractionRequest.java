package com.example.demo.dto;
import java.util.List;

public class InteractionRequest {
    private List<Long> medicationIds;

    public InteractionRequest() {}
    public List<Long> getMedicationIds() { return medicationIds; }
    public void setMedicationIds(List<Long> medicationIds) { this.medicationIds = medicationIds; }
}