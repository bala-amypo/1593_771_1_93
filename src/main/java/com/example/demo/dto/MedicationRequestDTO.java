package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class MedicationRequestDTO {
    private Long id;
    private String name;
    private List<Long> ingredientIds;
}