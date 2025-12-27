package com.example.demo.service.impl;

import com.example.demo.model.ActiveIngredient;
import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.Medication;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.InteractionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final MedicationRepository medicationRepository;
    private final InteractionRuleRepository ruleRepository;
    private final InteractionCheckResultRepository resultRepository;

    public InteractionServiceImpl(MedicationRepository medicationRepository,
                                  InteractionRuleRepository ruleRepository,
                                  InteractionCheckResultRepository resultRepository) {
        this.medicationRepository = medicationRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {

        List<Medication> medications =
                medicationRepository.findAllById(medicationIds);

        Set<ActiveIngredient> ingredients = new HashSet<>();
        medications.forEach(m -> ingredients.addAll(m.getIngredients()));

        List<String> foundInteractions = new ArrayList<>();

        List<ActiveIngredient> list = new ArrayList<>(ingredients);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {

                ruleRepository.findRuleBetweenIngredients(
                        list.get(i).getId(),
                        list.get(j).getId()
                ).ifPresent(rule ->
                        foundInteractions.add(
                                rule.getIngredientA().getName() + " - " +
                                rule.getIngredientB().getName() + " (" +
                                rule.getSeverity() + ")"));
            }
        }

        InteractionCheckResult result = new InteractionCheckResult();
        result.setMedications(
                medications.stream()
                        .map(Medication::getName)
                        .reduce((a, b) -> a + "," + b)
                        .orElse("")
        );
        result.setInteractions(foundInteractions.toString());

        return resultRepository.save(result);
    }

    @Override
    public InteractionCheckResult getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() ->
                        new RuntimeException("Result not found"));
    }
}
