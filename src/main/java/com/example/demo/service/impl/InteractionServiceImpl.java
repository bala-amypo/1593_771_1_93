// package com.example.demo.service;

// import org.springframework.stereotype.Service;
// import java.util.*;

// import com.example.demo.model.*;
// import com.example.demo.repository.*;

// @Service
// public class InteractionServiceImpl implements InteractionService {

//     private final MedicationRepository medicationRepo;
//     private final InteractionRuleRepository ruleRepo;
//     private final InteractionCheckResultRepository resultRepo;

//     public InteractionServiceImpl(
//             MedicationRepository medicationRepo,
//             InteractionRuleRepository ruleRepo,
//             InteractionCheckResultRepository resultRepo) {

//         this.medicationRepo = medicationRepo;
//         this.ruleRepo = ruleRepo;
//         this.resultRepo = resultRepo;
//     }

//     @Override
//     public InteractionCheckResult checkInteractions(List<Long> medicationIds) {

//         List<Medication> meds =
//                 medicationRepo.findAllById(medicationIds);

//         Set<String> findings = new LinkedHashSet<>();

//         for (int i = 0; i < meds.size(); i++) {
//             for (int j = i + 1; j < meds.size(); j++) {

//                 for (ActiveIngredient a : meds.get(i).getIngredients()) {
//                     for (ActiveIngredient b : meds.get(j).getIngredients()) {

//                         InteractionRule rule =
//                                 ruleRepo.findRuleBetweenIngredients(
//                                         a.getId(), b.getId());

//                         if (rule != null) {
//                             findings.add(rule.getSeverity()
//                                     + " : " + rule.getDescription());
//                         }
//                     }
//                 }
//             }
//         }

//         InteractionCheckResult result = new InteractionCheckResult();
//         result.setMedications(medicationIds.toString());
//         result.setInteractions(findings.toString());

//         return resultRepo.save(result);
//     }

//     @Override
//     public InteractionCheckResult getResult(Long resultId) {
//         return resultRepo.findById(resultId).orElse(null);
//     }
// }