package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ActiveIngredientRepository ingredientRepo;
    private final MedicationRepository medicationRepo;

    public CatalogServiceImpl(
            ActiveIngredientRepository ingredientRepo,
            MedicationRepository medicationRepo) {
        this.ingredientRepo = ingredientRepo;
        this.medicationRepo = medicationRepo;
    }

    @Override
    public ActiveIngredient createIngredient(IngredientDTO dto) {

        if (ingredientRepo.existsByName(dto.getName())) {
            return ingredientRepo.findByName(dto.getName());
        }

        ActiveIngredient ingredient = new ActiveIngredient();
        ingredient.setName(dto.getName());

        return ingredientRepo.save(ingredient);
    }

    @Override
    public Medication createMedication(MedicationDTO dto) {

        Medication med = new Medication();
        med.setName(dto.getName());

        List<ActiveIngredient> ingredients =
                ingredientRepo.findAllById(dto.getIngredientIds());

        med.setIngredients(ingredients);
        return medicationRepo.save(med);
    }
}