// package com.example.demo.service;

// import com.example.demo.model.User;

// public interface UserService {

//     User register(User user);

//     User findByEmail(String email);
// }



package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    // ✅ MUST be declared here
    User registerUser(User user);

    User findByEmail(String email);
}
