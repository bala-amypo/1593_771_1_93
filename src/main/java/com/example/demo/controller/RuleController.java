package com.example.demo.controller;

import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Interaction Rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Operation(summary = "Add interaction rule")
    @PostMapping
    public ResponseEntity<InteractionRule> addRule(
            @RequestBody InteractionRule rule) {

        return ResponseEntity.ok(
                ruleService.addRule(rule)
        );
    }

    @Operation(summary = "Get all interaction rules")
    @GetMapping
    public ResponseEntity<List<InteractionRule>> getAllRules() {

        return ResponseEntity.ok(
                ruleService.getAllRules()
        );
    }
}
