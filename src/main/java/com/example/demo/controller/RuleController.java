// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.InteractionRule;
// import com.example.demo.service.RuleService;

// @RestController
// @RequestMapping("/rules")
// public class RuleController {

//     private final RuleService ruleService;

//     public RuleController(RuleService ruleService) {
//         this.ruleService = ruleService;
//     }

//     @PostMapping
//     public InteractionRule addRule(@RequestBody InteractionRule rule) {
//         return ruleService.addRule(rule);
//     }

//     @GetMapping
//     public List<InteractionRule> getAllRules() {
//         return ruleService.getAllRules();
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private final RuleService ruleService;

    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<InteractionRule> addRule(@RequestBody InteractionRule rule) {
        return ResponseEntity.status(201).body(ruleService.addRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<InteractionRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}