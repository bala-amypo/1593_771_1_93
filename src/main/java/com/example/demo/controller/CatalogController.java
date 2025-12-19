package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;
    private final ActiveIngredientRepository ingredientRepository;

    public CatalogController(CatalogService catalogService,
                             ActiveIngredientRepository ingredientRepository) {
        this.catalogService = catalogService;
        this.ingredientRepository = ingredientRepository;
    }
    @PostMapping("/ingredient")
    public ActiveIngredient addIngredient(@RequestBody ActiveIngredient ingredient) {
        return catalogService.addIngredient(ingredient);
    }
    @PostMapping("/medication")
    public Medication addMedication(@RequestBody Medication medication) {

        Set<ActiveIngredient> resolvedIngredients =
                medication.getIngredients().stream()
                        .map(i -> ingredientRepository.findById(i.getId()).orElse(null))
                        .filter(i -> i != null)
                        .collect(java.util.stream.Collectors.toSet());

        medication.getIngredients().clear();
        resolvedIngredients.forEach(medication::addIngredient);

        return catalogService.addMedication(medication);
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return catalogService.getAllMedications();
    }
}
