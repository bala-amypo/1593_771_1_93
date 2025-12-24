// package com.example.demo.dto;

// import java.time.LocalDateTime;

// public class InteractionResponse {

//     private String medications;
//     private String interactions;
//     private LocalDateTime checkedAt;

//     public InteractionResponse() {
//     }

//     public InteractionResponse(String medications, String interactions, LocalDateTime checkedAt) {
//         this.medications = medications;
//         this.interactions = interactions;
//         this.checkedAt = checkedAt;
//     }

//     // getters
//     public String getMedications() {
//         return medications;
//     }

//     public String getInteractions() {
//         return interactions;
//     }

//     public LocalDateTime getCheckedAt() {
//         return checkedAt;
//     }

//     // setters
//     public void setMedications(String medications) {
//         this.medications = medications;
//     }

//     public void setInteractions(String interactions) {
//         this.interactions = interactions;
//     }

//     public void setCheckedAt(LocalDateTime checkedAt) {
//         this.checkedAt = checkedAt;
//     }
// }




package com.example.demo.dto;

import java.time.LocalDateTime;

public class InteractionResponseDTO {

    private Long id;
    private String medications;
    private String interactions;
    private LocalDateTime checkedAt;

    public InteractionResponseDTO() {
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
