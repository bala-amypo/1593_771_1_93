package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InteractionCheckResultDTO {
    private Long resultId;
    private String medicationNames;
    private List<InteractionDto> matchedInteractions;
    private String highestSeverity;
    private LocalDateTime checkedAt;
}