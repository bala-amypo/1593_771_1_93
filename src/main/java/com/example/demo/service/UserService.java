package com.example.demo2.service;

import com.example.demo2.model.User;

public interface UserService {

    User registerUser(User user);

    User findByEmail(String email);
}
