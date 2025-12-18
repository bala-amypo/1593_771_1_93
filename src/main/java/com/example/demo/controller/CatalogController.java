package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.IngredientDto;
import com.example.demo.dto.MedicationDto;
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
    public ActiveIngredient addIngredient(@RequestBody IngredientDto dto) {
        return catalogService.addIngredient(new ActiveIngredient(dto.getName()));
    }

    @PostMapping("/medication")
    public Medication addMedication(@RequestBody MedicationDto dto) {

        Medication medication = new Medication(dto.getName());

        dto.getIngredientIds().forEach(id ->
                ingredientRepository.findById(id)
                        .ifPresent(medication::addIngredient)
        );

        return catalogService.addMedication(medication);
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return catalogService.getAllMedications();
    }
}
