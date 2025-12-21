package com.example.demo.service.impl;

import com.example.demo.model.ActiveIngredient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "interaction_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ingredient_a_id", "ingredient_b_id"})
    }
)
public class RuleServiceImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_a_id")
    private ActiveIngredient ingredientA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ingredient_b_id")
    private ActiveIngredient ingredientB;

    @Column(nullable = false)
    private String severity; // MINOR, MODERATE, MAJOR

    private String description;

    public InteractionRule() {}

    public InteractionRule(
            ActiveIngredient ingredientA,
            ActiveIngredient ingredientB,
            String severity,
            String description) {
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.severity = severity;
        this.description = description;
    }

    // ✅ REQUIRED GETTERS
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

    // ✅ REQUIRED SETTERS
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
