// package com.example.demo2.model;

// import jakarta.persistence.*;

// @Entity
// @Table(
//     name = "interaction_rules",
//     uniqueConstraints = @UniqueConstraint(
//         columnNames = {"ingredient_a_id", "ingredient_b_id"}
//     )
// )
// public class InteractionRule {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "ingredient_a_id", nullable = false)
//     private ActiveIngredient ingredientA;

//     @ManyToOne
//     @JoinColumn(name = "ingredient_b_id", nullable = false)
//     private ActiveIngredient ingredientB;

//     @Column(nullable = false)
//     private String severity;

//     @Column(length = 500)
//     private String description;

//     public InteractionRule() {
//     }
//     public InteractionRule(
//             ActiveIngredient ingredientA,
//             ActiveIngredient ingredientB,
//             String severity,
//             String description) {
//         this.ingredientA = ingredientA;
//         this.ingredientB = ingredientB;
//         this.severity = severity;
//         this.description = description;
//     }

//     public Long getId() {
//         return id;
//     }

//     public ActiveIngredient getIngredientA() {
//         return ingredientA;
//     }

//     public ActiveIngredient getIngredientB() {
//         return ingredientB;
//     }

//     public String getSeverity() {
//         return severity;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setIngredientA(ActiveIngredient ingredientA) {
//         this.ingredientA = ingredientA;
//     }

//     public void setIngredientB(ActiveIngredient ingredientB) {
//         this.ingredientB = ingredientB;
//     }

//     public void setSeverity(String severity) {
//         this.severity = severity;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

// }

