// package com.example.demo.service;

// import org.springframework.stereotype.Service;
// import com.example.demo.dto.RuleDTO;
// import com.example.demo.model.*;
// import com.example.demo.repository.*;

// @Service
// public class RuleServiceImpl implements RuleService {

//     private final ActiveIngredientRepository ingredientRepo;
//     private final InteractionRuleRepository ruleRepo;

//     public RuleServiceImpl(
//             ActiveIngredientRepository ingredientRepo,
//             InteractionRuleRepository ruleRepo) {
//         this.ingredientRepo = ingredientRepo;
//         this.ruleRepo = ruleRepo;
//     }

//     @Override
//     public InteractionRule addRule(RuleDTO dto) {

//         ActiveIngredient a =
//                 ingredientRepo.findById(dto.getIngredientAId())
//                 .orElseThrow();

//         ActiveIngredient b =
//                 ingredientRepo.findById(dto.getIngredientBId())
//                 .orElseThrow();

//         InteractionRule rule = new InteractionRule();
//         rule.setIngredientA(a);
//         rule.setIngredientB(b);
//         rule.setSeverity(dto.getSeverity());
//         rule.setDescription(dto.getDescription());

//         return ruleRepo.save(rule);
//     }
// }