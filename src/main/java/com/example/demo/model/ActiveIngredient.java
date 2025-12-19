package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "active_ingredients",
    uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class ActiveIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // No-arg constructor (required by JPA)
    public ActiveIngredient() {
    }

    // Parameterized constructor
    public ActiveIngredient(String name) {
        this.name = name;
    }

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
