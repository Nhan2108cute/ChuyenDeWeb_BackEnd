// service/UserService.java
package com.example.backend.service;

import com.example.backend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User authenticate(String username, String password);
}

