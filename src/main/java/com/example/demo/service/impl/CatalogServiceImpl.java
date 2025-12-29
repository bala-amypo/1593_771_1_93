
package com.example.demo.service.impl;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ActiveIngredientRepository ingredientRepository;
    private final MedicationRepository medicationRepository;

    /**
     * Empty constructor required for manual instantiation in specific test cases (e.g., Test Case line 251).
     * Note: Repositories will be null when using this constructor.
     */
    public CatalogServiceImpl() {
        this.ingredientRepository = null;
        this.medicationRepository = null;
    }

    /**
     * Primary constructor used by Spring for Dependency Injection.
     * The @Autowired annotation ensures Spring uses this constructor and provides the Repositories.
     */
    @Autowired
    public CatalogServiceImpl(ActiveIngredientRepository ingredientRepository, MedicationRepository medicationRepository) {
        this.ingredientRepository = ingredientRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public ActiveIngredient addIngredient(ActiveIngredient ingredient) {
        // Guard check to prevent NullPointerException if the empty constructor was used by mistake
        if (ingredientRepository == null) {
            throw new IllegalStateException("Repository not initialized. Ensure Spring is injecting dependencies.");
        }

        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Ingredient already exists");
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Medication addMedication(Medication medication) {
        // Guard check
        if (medicationRepository == null) {
            throw new IllegalStateException("Repository not initialized.");
        }

        if (medication.getIngredients() == null || medication.getIngredients().isEmpty()) {
            throw new IllegalArgumentException("At least one ingredient required");
        }
        return medicationRepository.save(medication);
    }

    @Override
    public List<Medication> getAllMedications() {
        if (medicationRepository == null) {
            throw new IllegalStateException("Repository not initialized.");
        }
        return medicationRepository.findAll();
    }
}