package com.example.demo2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo2.model.InteractionRule;

public interface InteractionRuleRepository
        extends JpaRepository<InteractionRule, Long> {

    @Query("""
        SELECT r FROM InteractionRule r
        WHERE (r.ingredientA.id = :id1 AND r.ingredientB.id = :id2)
           OR (r.ingredientA.id = :id2 AND r.ingredientB.id = :id1)
    """)
    Optional<InteractionRule> findRuleBetweenIngredients(Long id1, Long id2);
}
