package com.example.demo.dto;

import java.util.List;

public class MedicationRequest {

    private String name;
    private List<Long> ingredientIds;

    public MedicationRequest() {
    }

    public MedicationRequest(String name, List<Long> ingredientIds) {
        this.name = name;
        this.ingredientIds = ingredientIds;
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Long> getIngredientIds() {
        return ingredientIds;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientIds(List<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}
