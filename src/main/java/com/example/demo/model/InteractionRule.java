package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "interaction_rules")
public class InteractionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REMOVED THE UNDERSCORE TO MATCH THE DATABASE ERROR
    @ManyToOne
    @JoinColumn(name = "ingredienta_id")
    private ActiveIngredient ingredientA;

    // REMOVED THE UNDERSCORE TO MATCH THE DATABASE ERROR
    @ManyToOne
    @JoinColumn(name = "ingredientb_id")
    private ActiveIngredient ingredientB;

    private String severity; // MINOR, MODERATE, MAJOR
    private String description;

    // No-arg constructor (Rule 2.4)
    public InteractionRule() {}

    // Field constructor (Rule 2.4)
    public InteractionRule(ActiveIngredient ingredientA, ActiveIngredient ingredientB, String severity, String description) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.severity = severity;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ActiveIngredient getIngredientA() { return ingredientA; }
    public void setIngredientA(ActiveIngredient ingredientA) { this.ingredientA = ingredientA; }

    public ActiveIngredient getIngredientB() { return ingredientB; }
    public void setIngredientB(ActiveIngredient ingredientB) { this.ingredientB = ingredientB; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}