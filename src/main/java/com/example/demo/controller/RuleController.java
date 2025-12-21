package com.example.demo2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.model.InteractionRule;
import com.example.demo2.service.RuleService;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * POST /rules
     * Add an interaction rule
     */
    @PostMapping
    public InteractionRule addRule(
            @RequestBody InteractionRule rule) {
        return ruleService.addRule(rule);
    }

    /**
     * GET /rules
     * Get all interaction rules
     */
    @GetMapping
    public List<InteractionRule> getAllRules() {
        return ruleService.getAllRules();
    }
}
