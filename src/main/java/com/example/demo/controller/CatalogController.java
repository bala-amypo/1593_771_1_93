// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.ActiveIngredient;
// import com.example.demo.model.Medication;
// import com.example.demo.service.CatalogService;

// @RestController
// @RequestMapping("/catalog")
// public class CatalogController {

//     private final CatalogService catalogService;

//     public CatalogController(CatalogService catalogService) {
//         this.catalogService = catalogService;
//     }

//     @PostMapping("/ingredient")
//     public ActiveIngredient addIngredient(
//             @RequestBody ActiveIngredient ingredient) {
//         return catalogService.addIngredient(ingredient);
//     }

//     @PostMapping("/medication")
//     public Medication addMedication(
//             @RequestBody Medication medication) {
//         return catalogService.addMedication(medication);
//     }

//     @GetMapping("/medications")
//     public List<Medication> getAllMedications() {
//         return catalogService.getAllMedications();
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.Medication;
import com.example.demo.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @PostMapping("/ingredient")
    public ActiveIngredient addIngredient(@RequestBody ActiveIngredient ingredient) {
        return service.addIngredient(ingredient);
    }

    @PostMapping("/medication")
    public Medication addMedication(@RequestBody Medication medication) {
        return service.addMedication(medication);
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return service.getAllMedications();
    }
}
