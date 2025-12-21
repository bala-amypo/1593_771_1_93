package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "interaction_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ingredienta_id", "ingredientb_id"})
    }
)
public class InteractionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredienta_id")
    private ActiveIngredient ingredientA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredientb_id")
    private ActiveIngredient ingredientB;

    @Column(nullable = false)
    private String severity; // MINOR, MODERATE, MAJOR

    private String description;

    public InteractionRule() {}

    public Long getId() {
        return id;
    }

    public ActiveIngredient getIngredientA() {
        return ingredientA;
    }

    public ActiveIngredient getIngredientB() {
        return ingredientB;
    }

    public String getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIngredientA(ActiveIngredient ingredientA) {
        this.ingredientA = ingredientA;
    }

    public void setIngredientB(ActiveIngredient ingredientB) {
        this.ingredientB = ingredientB;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
