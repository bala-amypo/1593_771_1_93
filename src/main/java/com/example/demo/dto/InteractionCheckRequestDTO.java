// package com.example.demo.dto;

// import java.util.List;

// public class InteractionCheckRequest {

//     private List<Long> medicationIds;

//     public InteractionCheckRequest() {
//     }

//     public InteractionCheckRequest(List<Long> medicationIds) {
//         this.medicationIds = medicationIds;
//     }

//     // getter
//     public List<Long> getMedicationIds() {
//         return medicationIds;
//     }

//     // setter
//     public void setMedicationIds(List<Long> medicationIds) {
//         this.medicationIds = medicationIds;
//     }
// }





package com.example.demo.dto;

import java.util.List;

public class InteractionCheckRequestDTO {

    private List<Long> medicationIds;

    public InteractionCheckRequestDTO() {
    }

    public List<Long> getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(List<Long> medicationIds) {
        this.medicationIds = medicationIds;
    }
}
