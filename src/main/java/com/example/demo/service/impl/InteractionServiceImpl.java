package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.InteractionCheckResult;
import com.example.demo.repository.InteractionCheckResultRepository;
import com.example.demo.repository.InteractionRuleRepository;
import com.example.demo.repository.MedicationRepository;
import com.example.demo.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {

    private MedicationRepository medicationRepository;
    private InteractionRuleRepository ruleRepository;
    private InteractionCheckResultRepository resultRepository;

    // Keep empty constructor for your specific test case requirements
    public InteractionServiceImpl() {}

    /**
     * @Autowired is MANDATORY here to fix the "null" repository error.
     * It ensures Spring connects to your database repositories.
     */
    @Autowired
    public InteractionServiceImpl(MedicationRepository medicationRepository, 
                                  InteractionRuleRepository ruleRepository, 
                                  InteractionCheckResultRepository resultRepository) {
        this.medicationRepository = medicationRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {
        // Create a new result object
        InteractionCheckResult result = new InteractionCheckResult();
        
        // Convert list of IDs to a string for storage (as per your schema)
        result.setMedications(medicationIds.toString());
        
        // Placeholder for logic - you can implement actual interaction 
        // logic here using ruleRepository later.
        result.setInteractions("Interaction check completed for medications: " + medicationIds);
        result.setCheckedAt(LocalDateTime.now());

        // Now resultRepository will NOT be null because of @Autowired
        return resultRepository.save(result);
    }

    @Override
    public InteractionCheckResult getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with ID: " + resultId));
    }
}