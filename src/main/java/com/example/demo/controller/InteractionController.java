package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;

@RestController
@RequestMapping("/interact")
public class InteractionController {

    private final InteractionService service;

    public InteractionController(InteractionService service) {
        this.service = service;
    }

    @PostMapping("/check")
    public InteractionCheckResult check(
            @RequestBody List<Long> medicationIds) {
        return service.checkInteractions(medicationIds);
    }

    @GetMapping("/result/{id}")
    public InteractionCheckResult result(@PathVariable Long id) {
        return service.getResult(id);
    }
}
