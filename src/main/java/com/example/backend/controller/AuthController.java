package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserServiceImpl;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Tên đăng nhập đã tồn tại!");
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword((String) userMap.get("password"));
            user.setEmail((String) userMap.get("email"));
            user.setPhone((String) userMap.get("phone"));
            Object accountTypeObj = userMap.get("accountType");
            int accountType = 1; // Mặc định là 1

            if (accountTypeObj instanceof Number) {
                accountType = ((Number) accountTypeObj).intValue();
            }
            user.setAccountType(accountType);


            String birthdayStr = (String) userMap.get("birthday");
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                LocalDate birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ISO_DATE);
                user.setBirthday(birthday);
            }

            userService.saveUser(user);
            return ResponseEntity.ok("Đăng ký thành công!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi tạo người dùng: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng!");
        }

        Map<String, Object> data = Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "accountType", user.getAccountType()
        );

        return ResponseEntity.ok(data);
    }

    // Có thể thêm API lấy danh sách user để frontend dùng load list, ví dụ:
//    @GetMapping("/users")
//    public ResponseEntity<?> getAllUsers() {
//        return ResponseEntity.ok(userRepository.findAll());
//    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
