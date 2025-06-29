package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.UsersResponse;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserSevice service;

    @PostMapping("/user/user-info")
    public ResponseEntity<String> userInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return ResponseEntity.ok(service.getUserDetails(token));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UsersResponse> userProfile() {
        Users users = service.getUsersProfile();

        UsersResponse usersResponse = new UsersResponse();

        usersResponse.setFullName(users.getFullname());
        usersResponse.setEmail(users.getEmail());
        usersResponse.setPhone(users.getPhone());
        usersResponse.setUsername(users.getUsername());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(users.getCreatedAt());

        usersResponse.setCreatedAt(formattedDate);

        return ResponseEntity.ok(usersResponse);
    }
}
