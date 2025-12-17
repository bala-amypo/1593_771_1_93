// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import org.springframework.web.bind.annotation.*;
// import com.example.demo.dto.*;
// import com.example.demo.model.*;
// import com.example.demo.service.CatalogService;

// @RestController
// @RequestMapping("/catalog")
// public class CatalogController {

//     private final CatalogService service;

//     public CatalogController(CatalogService service) {
//         this.service = service;
//     }

//     @PostMapping("/ingredient")
//     public ActiveIngredient addIngredient(
//             @RequestBody IngredientDTO dto) {
//         return service.createIngredient(dto);
//     }

//     @PostMapping("/medication")
//     public Medication addMedication(
//             @RequestBody MedicationDTO dto) {
//         return service.createMedication(dto);
//     }
// }
