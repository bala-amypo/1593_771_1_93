// package com.example.demo.service.impl;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.InteractionCheckResult;
// import com.example.demo.model.Medication;
// import com.example.demo.repository.InteractionCheckResultRepository;
// import com.example.demo.repository.InteractionRuleRepository;
// import com.example.demo.repository.MedicationRepository;
// import com.example.demo.service.InteractionService;

// @Service
// public class InteractionServiceImpl implements InteractionService {

//     private final MedicationRepository medicationRepository;
//     private final InteractionRuleRepository ruleRepository;
//     private final InteractionCheckResultRepository resultRepository;

//     public InteractionServiceImpl(MedicationRepository medicationRepository,
//                                   InteractionRuleRepository ruleRepository,
//                                   InteractionCheckResultRepository resultRepository) {
//         this.medicationRepository = medicationRepository;
//         this.ruleRepository = ruleRepository;
//         this.resultRepository = resultRepository;
//     }

//     @Override
//     public InteractionCheckResult checkInteractions(List<Long> medicationIds) {

//         List<Medication> medications =
//                 medicationRepository.findAllById(medicationIds);

//         if (medications.isEmpty()) {
//             throw new IllegalArgumentException("No medications found");
//         }

//         String medicationNames = medications.stream()
//                 .map(Medication::getName)
//                 .collect(Collectors.joining(", "));

//         // Simple JSON-like summary for test helper
//         String interactionJson =
//                 "{ \"totalRules\": " + ruleRepository.count() + " }";

//         InteractionCheckResult result =
//                 new InteractionCheckResult(medicationNames, interactionJson);

//         return resultRepository.save(result);
//     }

//     @Override
//     public InteractionCheckResult getResult(Long resultId) {

//         return resultRepository.findById(resultId)
//                 .orElseThrow(() ->
//                         new ResourceNotFoundException(
//                                 "Interaction result not found: " + resultId
//                         ));
//     }
// }




package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.InteractionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InteractionServiceImpl implements InteractionService {
    private final MedicationRepository medicationRepository;
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;
    private final ObjectMapper objectMapper;

    public InteractionServiceImpl(MedicationRepository medicationRepo, 
                                  InteractionRuleRepository ruleRepo, 
                                  InteractionCheckResultRepository resultRepo, 
                                  ObjectMapper objectMapper) {
        this.medicationRepository = medicationRepo;
        this.ruleRepository = ruleRepo;
        this.resultRepository = resultRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        List<Medication> medications = medicationRepository.findAllById(medicationIds);
        String medNames = medications.stream().map(Medication::getName).collect(Collectors.joining(", "));
        
        Set<ActiveIngredient> allIngredients = medications.stream()
                .flatMap(m -> m.getIngredients().stream())
                .collect(Collectors.toSet());

        List<ActiveIngredient> ingredientList = new ArrayList<>(allIngredients);
        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode interactionsArray = rootNode.putArray("interactions");

        for (int i = 0; i < ingredientList.size(); i++) {
            for (int j = i + 1; j < ingredientList.size(); j++) {
                Optional<InteractionRule> rule = ruleRepository.findRuleBetweenIngredients(
                        ingredientList.get(i).getId(), ingredientList.get(j).getId());
                
                if (rule.isPresent()) {
                    ObjectNode ruleNode = interactionsArray.addObject();
                    ruleNode.put("ingredientA", rule.get().getIngredientA().getName());
                    ruleNode.put("ingredientB", rule.get().getIngredientB().getName());
                    ruleNode.put("severity", rule.get().getSeverity());
                    ruleNode.put("description", rule.get().getDescription());
                }
            }
        }

        InteractionCheckResult result = new InteractionCheckResult(medNames, rootNode.toString());
        return resultRepository.save(result);
    }

    @Override
    public InteractionCheckResult getResult(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found"));
    }
}