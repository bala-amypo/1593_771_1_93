package com.example.demo.controller;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interact")
@Tag(name = "Drug Interaction")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @Operation(summary = "Check drug interactions")
    @PostMapping("/check")
    public ResponseEntity<InteractionCheckResult> checkInteractions(
            @RequestBody List<Long> medicationIds) {

        return ResponseEntity.ok(
                interactionService.checkInteractions(medicationIds)
        );
    }

    @Operation(summary = "Get previous interaction check result")
    @GetMapping("/result/{id}")
    public ResponseEntity<InteractionCheckResult> getResult(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interactionService.getResult(id)
        );
    }
}
