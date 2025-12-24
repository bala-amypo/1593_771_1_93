// package com.example.demo.service;

// import java.util.List;
// import java.util.Optional;

// import com.example.demo.model.InteractionRule;

// public interface RuleService {

//     InteractionRule addRule(InteractionRule rule);

//     Optional<InteractionRule> findRule(Long ingredientAId, Long ingredientBId);

//     List<InteractionRule> getAllRules();
// }


package com.example.demo.service;

import com.example.demo.model.InteractionRule;

import java.util.List;

public interface RuleService {
    InteractionRule addRule(InteractionRule rule);
    List<InteractionRule> getAllRules();
}
