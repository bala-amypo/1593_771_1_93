package com.example.demo.dto;

public class IngredientRequest {

    private String name;

    public IngredientRequest() {
    }

    public IngredientRequest(String name) {
        this.name = name;
    }

    // getter
    public String getName() {
        return name;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }
}
