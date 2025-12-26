package com.example.demo.dto;
import java.util.List;
public class MedicationRequestDTO {
    private String name;
    private List<Long> ingredientIds;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Long> getIngredientIds() { return ingredientIds; }
    public void setIngredientIds(List<Long> ids) { this.ingredientIds = ids; }
}