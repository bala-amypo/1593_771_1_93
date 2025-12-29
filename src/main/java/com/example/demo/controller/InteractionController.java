

package com.example.demo.controller;

import com.example.demo.dto.InteractionRequest;
import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interact")
@Tag(name = "Interactions", description = "Evaluate Medication Combinations")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping("/check")
    @Operation(summary = "Check for interactions between a list of medication IDs")
    public ResponseEntity<InteractionCheckResult> check(@RequestBody InteractionRequest request) {
        // Test case 14 expects service.checkInteractions(List<Long>)
        return ResponseEntity.ok(interactionService.checkInteractions(request.getMedicationIds()));
    }

    @GetMapping("/result/{id}")
    @Operation(summary = "Retrieve a previous check result by ID")
    public ResponseEntity<InteractionCheckResult> getResult(@PathVariable Long id) {
        // Test case 15 and 54 expect service.getResult(Long)
        return ResponseEntity.ok(interactionService.getResult(id));
    }
}