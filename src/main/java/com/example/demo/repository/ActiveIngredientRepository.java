package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ActiveIngredient;

public interface ActiveIngredientRepository
        extends JpaRepository<ActiveIngredient, Long> {

    Optional<ActiveIngredient> findByName(String name);

    boolean existsByName(String name);
}
