package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import com.example.demo.repository.ActiveIngredientRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;

@Service
public class RuleServiceImpl implements RuleService {

    private final InteractionRuleRepository ruleRepository;
    private final ActiveIngredientRepository ingredientRepository;

    public RuleServiceImpl(
            InteractionRuleRepository ruleRepository,
            ActiveIngredientRepository ingredientRepository) {
        this.ruleRepository = ruleRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public InteractionRule addRule(InteractionRule rule) {

        Long aId = rule.getIngredientA().getId();
        Long bId = rule.getIngredientB().getId();

        if (aId == null || aId <= 0 || bId == null || bId <= 0) {
            throw new IllegalArgumentException(
                    "Ingredient IDs must be existing (> 0)");
        }

        ActiveIngredient ingredientA =
                ingredientRepository.findById(aId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Ingredient A not found: " + aId));

        ActiveIngredient ingredientB =
                ingredientRepository.findById(bId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Ingredient B not found: " + bId));

        // prevent duplicate rule
        ruleRepository.findByIngredientAAndIngredientB(
                ingredientA, ingredientB
        ).ifPresent(r -> {
            throw new IllegalArgumentException(
                    "Interaction rule already exists");
        });

        rule.setId(null);
        rule.setIngredientA(ingredientA);
        rule.setIngredientB(ingredientB);

        return ruleRepository.save(rule);
    }
}
