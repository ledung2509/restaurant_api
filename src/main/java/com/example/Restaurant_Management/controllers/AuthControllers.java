package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.services.AuthService;
import com.example.Restaurant_Management.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthControllers {

    @Autowired
    private AuthService service;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/guest/register")
    public ResponseEntity<?> registerUsers(@RequestBody Users user){
        try{
            Users users = service.registerUser(user);
            //service.registerUser(user)
            return ResponseEntity.ok("Đăng ký thành công!!!");
        }catch (IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/guest/login")
    public ResponseEntity<?> loginUsers(@RequestBody Users user){

        String rs = service.loginUser(user);

        if(rs.contains("Đăng nhập thành công")){
            return ResponseEntity.ok(rs);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(rs);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserName(@AuthenticationPrincipal UserDetails userDetails){

        System.out.println("Username: " + userDetails.getUsername());
        Users user = service.findByUserName(userDetails.getUsername());

        return ResponseEntity.ok(user.getFullname());
    }
}
