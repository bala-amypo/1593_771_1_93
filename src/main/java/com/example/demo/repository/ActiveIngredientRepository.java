
package com.example.demo.repository;

import com.example.demo.model.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, Long> {
    // Required for Test 39
    Optional<ActiveIngredient> findByName(String name);
    
    // Required for Rule 6.2 and Test 40/43
    boolean existsByName(String name);
}