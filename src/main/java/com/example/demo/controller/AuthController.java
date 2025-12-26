// package com.example.demo.controller;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/register")
//     public User register(@RequestBody User user) {
//         return userService.registerUser(user);
//     }

//     @PostMapping("/login")
//     public String login(@RequestBody User request) {

//         User user = userService.findByEmail(request.getEmail());

//         if (!user.getPassword().equals(request.getPassword())) {
//             return "Invalid email or password";
//         }

//         return "Login successful for user: " + user.getEmail();
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, 
                          JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        
        User user = userService.findByEmail(email);
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }
}