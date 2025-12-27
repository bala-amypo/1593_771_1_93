package com.example.demo.repository;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.model.Medication;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionCheckResultRepository
        extends JpaRepository<InteractionCheckResult, Long> {

    List<InteractionCheckResult> findByUser(User user);

    List<InteractionCheckResult> findBySeverity(String severity);

    List<InteractionCheckResult> findByMedicationAOrMedicationB(
            Medication medicationA,
            Medication medicationB
    );
}
