package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String role;

    public User() {}

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}





package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ActiveIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public ActiveIngredient() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}





package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<ActiveIngredient> ingredients;

    public Medication() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<ActiveIngredient> getIngredients() { return ingredients; }

    public void setName(String name) { this.name = name; }
    public void setIngredients(List<ActiveIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}




package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class InteractionRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ActiveIngredient ingredientA;

    @ManyToOne
    private ActiveIngredient ingredientB;

    private String severity;
    private String description;

    public Long getId() { return id; }
    public ActiveIngredient getIngredientA() { return ingredientA; }
    public ActiveIngredient getIngredientB() { return ingredientB; }
    public String getSeverity() { return severity; }
    public String getDescription() { return description; }

    public void setIngredientA(ActiveIngredient ingredientA) {
        this.ingredientA = ingredientA;
    }

    public void setIngredientB(ActiveIngredient ingredientB) {
        this.ingredientB = ingredientB;
    }

    public void setSeverity(String severity) { this.severity = severity; }
    public void setDescription(String description) {
        this.description = description;
    }
}



package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InteractionCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medications;
    private String interactions;

    private LocalDateTime checkedAt;

    @PrePersist
    public void assignTime() {
        checkedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getMedications() { return medications; }
    public String getInteractions() { return interactions; }
    public LocalDateTime getCheckedAt() { return checkedAt; }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions;
    }
}



repository

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}





import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ActiveIngredient;

public interface ActiveIngredientRepository
        extends JpaRepository<ActiveIngredient, Long> {

    ActiveIngredient findByName(String name);
    boolean existsByName(String name);
}




import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Medication;

public interface MedicationRepository
        extends JpaRepository<Medication, Long> {}



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.InteractionRule;

public interface InteractionRuleRepository
        extends JpaRepository<InteractionRule, Long> {

    InteractionRule findRuleBetweenIngredients(Long id1, Long id2);
}






import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.InteractionCheckResult;

public interface InteractionCheckResultRepository
        extends JpaRepository<InteractionCheckResult, Long> {}





utility
package com.example.demo.util;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtUtil {

    private static final String KEY = "jwt-demo-key";

    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public static String extractRole(String token) {
        return extractClaims(token).get("role").toString();
    }

    private static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}




Service
package com.example.demo.service;

import java.util.List;
import com.example.demo.model.InteractionCheckResult;

public interface InteractionService {
    InteractionCheckResult checkInteractions(List<Long> medicationIds);
    InteractionCheckResult getResult(Long resultId);
}



package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final MedicationRepository medicationRepo;
    private final InteractionRuleRepository ruleRepo;
    private final InteractionCheckResultRepository resultRepo;

    public InteractionServiceImpl(
            MedicationRepository medicationRepo,
            InteractionRuleRepository ruleRepo,
            InteractionCheckResultRepository resultRepo) {

        this.medicationRepo = medicationRepo;
        this.ruleRepo = ruleRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public InteractionCheckResult checkInteractions(List<Long> medicationIds) {

        List<Medication> meds =
                medicationRepo.findAllById(medicationIds);

        Set<String> findings = new LinkedHashSet<>();

        for (int i = 0; i < meds.size(); i++) {
            for (int j = i + 1; j < meds.size(); j++) {

                for (ActiveIngredient a : meds.get(i).getIngredients()) {
                    for (ActiveIngredient b : meds.get(j).getIngredients()) {

                        InteractionRule rule =
                                ruleRepo.findRuleBetweenIngredients(
                                        a.getId(), b.getId());

                        if (rule != null) {
                            findings.add(rule.getSeverity()
                                    + " : " + rule.getDescription());
                        }
                    }
                }
            }
        }

        InteractionCheckResult result = new InteractionCheckResult();
        result.setMedications(medicationIds.toString());
        result.setInteractions(findings.toString());

        return resultRepo.save(result);
    }

    @Override
    public InteractionCheckResult getResult(Long resultId) {
        return resultRepo.findById(resultId).orElse(null);
    }
}


Controller

package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        return repo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User request) {
        User dbUser = repo.findByEmail(request.getEmail());
        return JwtUtil.generateToken(
                dbUser.getEmail(), dbUser.getRole());
    }
}


package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.model.InteractionCheckResult;
import com.example.demo.service.InteractionService;

@RestController
@RequestMapping("/interact")
public class InteractionController {

    private final InteractionService service;

    public InteractionController(InteractionService service) {
        this.service = service;
    }

    @PostMapping("/check")
    public InteractionCheckResult check(
            @RequestBody List<Long> medicationIds) {
        return service.checkInteractions(medicationIds);
    }

    @GetMapping("/result/{id}")
    public InteractionCheckResult result(@PathVariable Long id) {
        return service.getResult(id);
    }
}



Servlet

package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/hello-servlet")
public class SimpleHelloServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setStatus(200);
        response.setContentType("text/plain");
        response.getWriter()
                .write("Hello from Simple Hello Servlet");
    }
}
Security config

package com.example.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(
                "/auth/**",
                "/hello-servlet",
                "/swagger-ui/**"
            ).permitAll()
            .anyRequest().authenticated();

        return http.build();
    }
}
