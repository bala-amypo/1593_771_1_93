package com.example.demo.dto;

public class InteractionDto {
    private String ingredientA;
    private String ingredientB;
    private String severity;
    private String description;

    public InteractionDto() {}
    public InteractionDto(String a, String b, String s, String d) {
        this.ingredientA = a; this.ingredientB = b; this.severity = s; this.description = d;
    }

    public String getIngredientA() { return ingredientA; }
    public void setIngredientA(String a) { this.ingredientA = a; }
    public String getIngredientB() { return ingredientB; }
    public void setIngredientB(String b) { this.ingredientB = b; }
    public String getSeverity() { return severity; }
    public void setSeverity(String s) { this.severity = s; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
}