package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * POST /catalog/ingredient
     * Add a new active ingredient
     */
    @PostMapping("/ingredient")
    public ActiveIngredient addIngredient(
            @RequestBody ActiveIngredient ingredient) {
        return catalogService.addIngredient(ingredient);
    }

    /**
     * POST /catalog/medication
     * Add a medication with ingredients
     */
    @PostMapping("/medication")
    public Medication addMedication(
            @RequestBody Medication medication) {
        return catalogService.addMedication(medication);
    }

    /**
     * GET /catalog/medications
     * List all medications
     */
    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return catalogService.getAllMedications();
    }
}
