// controller/AuthController.java
package com.example.backend.controller;


import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Tên đăng nhập đã tồn tại!");
        }

        userRepository.save(user);  // Lưu vào DB thật
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng!");
        }

        return ResponseEntity.ok(Map.of("name", user.getUsername()));
    }
}
