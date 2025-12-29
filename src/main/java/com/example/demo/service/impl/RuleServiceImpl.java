


// package com.example.demo.service.impl;

// import com.example.demo.model.InteractionRule;
// import com.example.demo.repository.InteractionRuleRepository;
// import com.example.demo.service.RuleService;
// import org.springframework.beans.factory.annotation.Autowired; // Added this import
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class RuleServiceImpl implements RuleService {
    
//     private InteractionRuleRepository ruleRepository;

//     /**
//      * MANDATORY: Keep this to pass Test Case line 252.
//      * Note: This constructor is used by your test suite, but not by Spring during runtime.
//      */
//     public RuleServiceImpl() {
//     }

//     /**
//      * This constructor is used by Spring to inject the repository.
//      * The @Autowired annotation is REQUIRED because there are two constructors.
//      */
//     @Autowired
//     public RuleServiceImpl(InteractionRuleRepository ruleRepository) {
//         this.ruleRepository = ruleRepository;
//     }

//     @Override
//     public InteractionRule addRule(InteractionRule rule) {
//         // Validation to prevent saving null rules or handling null repository gracefully
//         if (ruleRepository == null) {
//             throw new IllegalStateException("InteractionRuleRepository is null. Ensure @Autowired is working.");
//         }
//         return ruleRepository.save(rule);
//     }

//     @Override
//     public List<InteractionRule> getAllRules() {
//         if (ruleRepository == null) {
//             throw new IllegalStateException("InteractionRuleRepository is null.");
//         }
//         return ruleRepository.findAll();
//     }
// }


package com.example.demo.service.impl;

import com.example.demo.model.InteractionRule;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    private final InteractionRuleRepository ruleRepository;

    /**
     * MANDATORY: Keep this for your Test Case requirements.
     */
    public RuleServiceImpl() {
        this.ruleRepository = null;
    }

    /**
     * Spring will use this constructor to inject the repository.
     */
    @Autowired
    public RuleServiceImpl(InteractionRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public InteractionRule addRule(InteractionRule rule) {
        // Defensive check to ensure the repository was injected
        if (ruleRepository == null) {
            throw new IllegalStateException("RuleRepository not initialized. Check Spring configuration.");
        }
        
        // Basic validation: ensure ingredients are provided in the request
        if (rule.getIngredientA() == null || rule.getIngredientB() == null) {
            throw new IllegalArgumentException("Both ingredientA and ingredientB must be provided.");
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<InteractionRule> getAllRules() {
        if (ruleRepository == null) {
            throw new IllegalStateException("RuleRepository not initialized.");
        }
        return ruleRepository.findAll();
    }
}