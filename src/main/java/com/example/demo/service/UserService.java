

package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user); // Name matches Test 13/51
    User findByEmail(String email);
}