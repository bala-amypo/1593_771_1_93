package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "medication_ingredients",
        joinColumns = @JoinColumn(name = "medication_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<ActiveIngredient> activeIngredients;

    public Medication() {}

    public Medication(String name, List<ActiveIngredient> activeIngredients) {
        this.name = name;
        this.activeIngredients = activeIngredients;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<ActiveIngredient> getActiveIngredients() {
        return activeIngredients;
    }

    public void setName(String name) { this.name = name; }
    public void setActiveIngredients(List<ActiveIngredient> activeIngredients) {
        this.activeIngredients = activeIngredients;
    }
}
