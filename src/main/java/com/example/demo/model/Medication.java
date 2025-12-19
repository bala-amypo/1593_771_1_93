package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<ActiveIngredient> ingredients = new HashSet<>();

    // No-arg constructor
    public Medication() {
    }

    // Parameterized constructor
    public Medication(String name) {
        this.name = name;
    }

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<ActiveIngredient> getIngredients() {
        return ingredients;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(Set<ActiveIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    // ===== Helper methods =====
    public void addIngredient(ActiveIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(ActiveIngredient ingredient) {
        this.ingredients.remove(ingredient);
    }
}
