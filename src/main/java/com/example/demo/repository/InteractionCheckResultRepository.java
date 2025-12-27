package com.example.demo.repository;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.User;
import com.example.demo.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionCheckResultRepository
        extends JpaRepository<InteractionCheckResult, Long> {

    // Find all interaction checks done by a specific user
    List<InteractionCheckResult> findByUser(User user);

    // Find interaction checks by severity (LOW, MODERATE, HIGH)
    List<InteractionCheckResult> findBySeverity(String severity);

    // Find interaction checks between two medications
    List<InteractionCheckResult> findByMedicationAAndMedicationB(
            Medication medicationA,
            Medication medicationB
    );

    // Find all interaction checks involving a medication (A or B)
    List<InteractionCheckResult> findByMedicationAOrMedicationB(
            Medication medicationA,
            Medication medicationB
    );
}
