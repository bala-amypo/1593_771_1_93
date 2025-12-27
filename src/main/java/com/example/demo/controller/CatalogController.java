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
@RequestMapping("/api/catalog")
@Tag(name = "Catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Operation(summary = "Add active ingredient")
    @PostMapping("/ingredient")
    public ResponseEntity<ActiveIngredient> addIngredient(
            @RequestBody ActiveIngredient ingredient) {

        return ResponseEntity.ok(
                catalogService.addIngredient(ingredient)
        );
    }

    @Operation(summary = "Add medication")
    @PostMapping("/medication")
    public ResponseEntity<Medication> addMedication(
            @RequestBody Medication medication) {

        return ResponseEntity.ok(
                catalogService.addMedication(medication)
        );
    }

    @Operation(summary = "Get all medications")
    @GetMapping("/medications")
    public ResponseEntity<List<Medication>> getAllMedications() {

        return ResponseEntity.ok(
                catalogService.getAllMedications()
        );
    }
}
