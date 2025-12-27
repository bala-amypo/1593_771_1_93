package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_check_results")
public class InteractionCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who performed the interaction check
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Medication A
    @ManyToOne
    @JoinColumn(name = "medication_a_id", nullable = false)
    private Medication medicationA;

    // Medication B
    @ManyToOne
    @JoinColumn(name = "medication_b_id", nullable = false)
    private Medication medicationB;

    // Result severity (LOW, MODERATE, HIGH)
    @Column(nullable = false)
    private String severity;

    // Detailed result message
    @Column(length = 1000)
    private String message;

    // Time when the check was performed
    @Column(nullable = false)
    private LocalDateTime checkedAt;

    // 🔹 Default constructor
    public InteractionCheckResult() {
        this.checkedAt = LocalDateTime.now();
    }

    // 🔹 Parameterized constructor
    public InteractionCheckResult(User user,
                                  Medication medicationA,
                                  Medication medicationB,
                                  String severity,
                                  String message) {
        this.user = user;
        this.medicationA = medicationA;
        this.medicationB = medicationB;
        this.severity = severity;
        this.message = message;
        this.checkedAt = LocalDateTime.now();
    }

    // 🔹 Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Medication getMedicationA() {
        return medicationA;
    }
 
    public void setMedicationA(Medication medicationA) {
        this.medicationA = medicationA;
    }
 
    public Medication getMedicationB() {
        return medicationB;
    }
 
    public void setMedicationB(Medication medicationB) {
        this.medicationB = medicationB;
    }
 
    public String getSeverity() {
        return severity;
    }
 
    public void setSeverity(String severity) {
        this.severity = severity;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }
 
    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
