package com.example.demo.repository;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteractionRuleRepository
        extends JpaRepository<InteractionRule, Long> {

    Optional<InteractionRule> findByIngredientAAndIngredientB(
            ActiveIngredient ingredientA,
            ActiveIngredient ingredientB
    );
}
