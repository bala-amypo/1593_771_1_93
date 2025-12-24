// package com.example.demo.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.example.demo.model.InteractionCheckResult;

// public interface InteractionCheckResultRepository
//         extends JpaRepository<InteractionCheckResult, Long> {
// }


package com.example.demo.repository;

import com.example.demo.model.InteractionCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionCheckResultRepository
        extends JpaRepository<InteractionCheckResult, Long> {
}
