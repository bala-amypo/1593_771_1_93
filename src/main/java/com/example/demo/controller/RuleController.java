package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private final RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }

    @PostMapping
    public InteractionRule createRule(
            @RequestBody RuleDTO dto) {
        return service.addRule(dto);
    }
}