package com.example.demo.entity;

public class Medication {
    private long id;
    private String name;
    private ActiveIngredient ingredients;

    public Medication(){}

    public Medication(String name, ActiveIngredient ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActiveIngredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(ActiveIngredient ingredients) {
        this.ingredients = ingredients;
    }

    
}
