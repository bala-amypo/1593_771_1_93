

// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {
//     private UserRepository userRepository;
//     private PasswordEncoder passwordEncoder;

//     // MANDATORY: Add this to pass Test Case line 250
//     public UserServiceImpl() {}

//     // Constructor injection for Spring
//     public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     public User register(User user) {
//         if (user.getRole() == null || user.getRole().isEmpty()) {
//             user.setRole("USER");
//         }
//         if (passwordEncoder != null && user.getPassword() != null) {
//             user.setPassword(passwordEncoder.encode(user.getPassword()));
//         }
//         return userRepository.save(user);
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//     }
// }






package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // Keep this for your Test Case line 250 if it's mandatory
    public UserServiceImpl() {}

    /**
     * @Autowired is CRITICAL here. 
     * It tells Spring: "Ignore the empty constructor and use this one 
     * to inject the Repository and PasswordEncoder."
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        // Set default role
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        // Encode password before saving
        if (passwordEncoder != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Now userRepository will not be null
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}