

package com.example.demo.controller;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@Tag(name = "Catalog", description = "Manage Ingredients and Medications")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/ingredient")
    @Operation(summary = "Add a new active ingredient")
    public ResponseEntity<ActiveIngredient> addIngredient(@RequestBody ActiveIngredient ingredient) {
        return ResponseEntity.ok(catalogService.addIngredient(ingredient));
    }

    @PostMapping("/medication")
    @Operation(summary = "Create a medication with ingredients")
    public ResponseEntity<Medication> addMedication(@RequestBody Medication medication) {
        // In a real scenario, you'd map IDs from a DTO here
        return ResponseEntity.ok(catalogService.addMedication(medication));
    }

    @GetMapping("/medications")
    @Operation(summary = "Get all medications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        return ResponseEntity.ok(catalogService.getAllMedications());
    }
}