// package com.example.demo.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.model.ActiveIngredient;
// import com.example.demo.model.InteractionRule;

// public interface InteractionRuleRepository
//         extends JpaRepository<InteractionRule, Long> {

//     Optional<InteractionRule> findByIngredientAAndIngredientB(
//             ActiveIngredient ingredientA,
//             ActiveIngredient ingredientB
//     );
// }



package com.example.demo.repository;

import com.example.demo.model.InteractionRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {

    @Query("""
        SELECT r FROM InteractionRule r
        WHERE (r.ingredientA.id = :a AND r.ingredientB.id = :b)
           OR (r.ingredientA.id = :b AND r.ingredientB.id = :a)
    """)
    Optional<InteractionRule> findRuleBetweenIngredients(Long a, Long b);
//    InteractionRule findByid(Long id);
}
