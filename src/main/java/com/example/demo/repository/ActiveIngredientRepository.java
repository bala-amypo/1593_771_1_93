// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.example.demo.model.ActiveIngredient;

// public interface ActiveIngredientRepository
//         extends JpaRepository<ActiveIngredient, Long> {
// }



package com.example.demo.repository;

import com.example.demo.model.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ActiveIngredientRepository extends JpaRepository<ActiveIngredient, Long> {
    /**
     * Retrieves an ingredient by its unique name.
     */
    Optional<ActiveIngredient> findByName(String name);

    /**
     * Checks if an ingredient with the given name already exists.
     */
    boolean existsByName(String name);
}