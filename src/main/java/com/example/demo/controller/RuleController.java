package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;

@RestController
@RequestMapping("/interactions")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * POST /interactions/rule
     */
    @PostMapping("/rule")
    public InteractionRule addRule(
            @RequestBody InteractionRule rule) {
        return ruleService.addRule(rule);
    }
}
