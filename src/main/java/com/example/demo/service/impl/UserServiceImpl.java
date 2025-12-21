package com.example.demo2.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo2.exception.ResourceNotFoundException;
import com.example.demo2.model.User;
import com.example.demo2.repository.UserRepository;
import com.example.demo2.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {

        if (user.getRole() == null) {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));
    }
}
