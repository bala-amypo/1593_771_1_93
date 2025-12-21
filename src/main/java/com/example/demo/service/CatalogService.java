package com.example.demo2.service;

import java.util.List;

import com.example.demo2.model.ActiveIngredient;
import com.example.demo2.model.Medication;

public interface CatalogService {

    ActiveIngredient addIngredient(ActiveIngredient ingredient);

    Medication addMedication(Medication medication);

    List<Medication> getAllMedications();
}
