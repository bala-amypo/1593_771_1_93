package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {

    // Find all rules where ingredient appears as A or B
    @Query("""
        SELECT r FROM InteractionRule r
        WHERE r.ingredientA.id = :ingredientId
           OR r.ingredientB.id = :ingredientId
    """)
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);

    // Find rule between two ingredients (unordered pair)
    @Query("""
        SELECT r FROM InteractionRule r
        WHERE (r.ingredientA.id = :id1 AND r.ingredientB.id = :id2)
           OR (r.ingredientA.id = :id2 AND r.ingredientB.id = :id1)
    """)
    Optional<InteractionRule> findRuleBetweenIngredients(
            @Param("id1") Long id1,
            @Param("id2") Long id2
    );
}
