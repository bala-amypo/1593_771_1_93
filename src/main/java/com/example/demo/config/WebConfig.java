package com.example.demo.dto;

public class AuthRequestDTO {

    private String email;
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}



package com.example.demo.dto;

public class IngredientDTO {

    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}



package com.example.demo.dto;

import java.util.List;

public class MedicationDTO {

    private String name;
    private List<Long> ingredientIds;

    public String getName() { return name; }
    public List<Long> getIngredientIds() { return ingredientIds; }

    public void setName(String name) { this.name = name; }
    public void setIngredientIds(List<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}



package com.example.demo.dto;

public class RuleDTO {

    private Long ingredientAId;
    private Long ingredientBId;
    private String severity;
    private String description;

    public Long getIngredientAId() { return ingredientAId; }
    public Long getIngredientBId() { return ingredientBId; }
    public String getSeverity() { return severity; }
    public String getDescription() { return description; }

    public void setIngredientAId(Long ingredientAId) {
        this.ingredientAId = ingredientAId;
    }

    public void setIngredientBId(Long ingredientBId) {
        this.ingredientBId = ingredientBId;
    }

    public void setSeverity(String severity) { this.severity = severity; }
    public void setDescription(String description) {
        this.description = description;
    }
}



package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.CatalogService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    @PostMapping("/ingredient")
    public ActiveIngredient addIngredient(
            @RequestBody IngredientDTO dto) {
        return service.createIngredient(dto);
    }

    @PostMapping("/medication")
    public Medication addMedication(
            @RequestBody MedicationDTO dto) {
        return service.createMedication(dto);
    }
}


package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.InteractionRule;
import com.example.demo.service.RuleService;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private final RuleService service;

    public RuleController(RuleService service) {
        this.service = service;
    }

    @PostMapping
    public InteractionRule createRule(
            @RequestBody RuleDTO dto) {
        return service.addRule(dto);
    }
}



package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
}



package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;

public interface CatalogService {

    ActiveIngredient createIngredient(IngredientDTO dto);
    Medication createMedication(MedicationDTO dto);
}



package com.example.demo.service;

import com.example.demo.dto.RuleDTO;
import com.example.demo.model.InteractionRule;

public interface RuleService {
    InteractionRule addRule(RuleDTO dto);
}


package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        return repo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }
}



package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.*;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final ActiveIngredientRepository ingredientRepo;
    private final MedicationRepository medicationRepo;

    public CatalogServiceImpl(
            ActiveIngredientRepository ingredientRepo,
            MedicationRepository medicationRepo) {
        this.ingredientRepo = ingredientRepo;
        this.medicationRepo = medicationRepo;
    }

    @Override
    public ActiveIngredient createIngredient(IngredientDTO dto) {

        if (ingredientRepo.existsByName(dto.getName())) {
            return ingredientRepo.findByName(dto.getName());
        }

        ActiveIngredient ingredient = new ActiveIngredient();
        ingredient.setName(dto.getName());

        return ingredientRepo.save(ingredient);
    }

    @Override
    public Medication createMedication(MedicationDTO dto) {

        Medication med = new Medication();
        med.setName(dto.getName());

        List<ActiveIngredient> ingredients =
                ingredientRepo.findAllById(dto.getIngredientIds());

        med.setIngredients(ingredients);
        return medicationRepo.save(med);
    }
}



package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.RuleDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class RuleServiceImpl implements RuleService {

    private final ActiveIngredientRepository ingredientRepo;
    private final InteractionRuleRepository ruleRepo;

    public RuleServiceImpl(
            ActiveIngredientRepository ingredientRepo,
            InteractionRuleRepository ruleRepo) {
        this.ingredientRepo = ingredientRepo;
        this.ruleRepo = ruleRepo;
    }

    @Override
    public InteractionRule addRule(RuleDTO dto) {

        ActiveIngredient a =
                ingredientRepo.findById(dto.getIngredientAId())
                .orElseThrow();

        ActiveIngredient b =
                ingredientRepo.findById(dto.getIngredientBId())
                .orElseThrow();

        InteractionRule rule = new InteractionRule();
        rule.setIngredientA(a);
        rule.setIngredientB(b);
        rule.setSeverity(dto.getSeverity());
        rule.setDescription(dto.getDescription());

        return ruleRepo.save(rule);
    }
}


package com.example.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.util.JwtUtil;

public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (JwtUtil.validateToken(token)) {

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                JwtUtil.extractUsername(token),
                                null,
                                null);

                SecurityContextHolder.getContext()
                        .setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}


package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(
            CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}




package com.example.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.web.*;
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
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                new JwtAuthenticationFilter(),
                SecurityContextHolderFilter.class
            );

        return http.build();
    }
}




package com.example.demo.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(
            Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
