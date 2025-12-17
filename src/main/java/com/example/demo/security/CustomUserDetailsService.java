package com.example.demo.security;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(
            UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found with email: " + email);
        }

        return new CustomerUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}