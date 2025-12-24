// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.Medication;

// public interface MedicationRepository
//         extends JpaRepository<Medication, Long> {
// }




package com.example.demo.repository;

import com.example.demo.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
