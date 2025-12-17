// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// public class InteractionRule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private ActiveIngredient ingredientA;

//     @ManyToOne
//     private ActiveIngredient ingredientB;

//     private String severity;
//     private String description;

//     public Long getId() { return id; }
//     public ActiveIngredient getIngredientA() { return ingredientA; }
//     public ActiveIngredient getIngredientB() { return ingredientB; }
//     public String getSeverity() { return severity; }
//     public String getDescription() { return description; }

//     public void setIngredientA(ActiveIngredient ingredientA) {
//         this.ingredientA = ingredientA;
//     }

//     public void setIngredientB(ActiveIngredient ingredientB) {
//         this.ingredientB = ingredientB;
//     }

//     public void setSeverity(String severity) { this.severity = severity; }
//     public void setDescription(String description) {
//         this.description = description;
//     }
// }