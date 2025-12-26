package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class EvaluateRequest {
    private List<Long> medicationIds;
    private List<String> drugCodes; // For SRS compatibility
    private boolean runAsync = false;
}