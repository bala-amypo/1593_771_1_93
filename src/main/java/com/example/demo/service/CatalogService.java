package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;

public interface CatalogService {

    ActiveIngredient createIngredient(IngredientDTO dto);
    Medication createMedication(MedicationDTO dto);
}