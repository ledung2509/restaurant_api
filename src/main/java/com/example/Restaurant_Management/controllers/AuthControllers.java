package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllers {

    //@Autowired
    private final AuthService service;

    @PostMapping("/guest/register")
    public ResponseEntity<Object> registerUsers(@RequestBody Users user) {
        try {
            service.registerUser(user);
            return ResponseEntity.ok("Đăng ký thành công!!!");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/guest/login")
    public ResponseEntity<Object> loginUsers(@RequestBody Users user) {

        String rs = service.loginUser(user);

        if (rs.contains("Đăng nhập thành công")) {
            return ResponseEntity.ok(rs);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(rs);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserName(@AuthenticationPrincipal UserDetails userDetails) {

        System.out.println("Username: " + userDetails.getUsername());
        Users user = service.findByUserName(userDetails.getUsername());

        return ResponseEntity.ok(user.getFullname());
    }
}
