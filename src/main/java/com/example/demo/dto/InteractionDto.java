package com.example.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractionDto {
    private String ingredientA;
    private String ingredientB;
    private String severity;
    private String description;
    private String recommendation;
}