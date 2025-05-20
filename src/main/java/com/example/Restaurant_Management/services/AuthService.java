package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.UserResponse;
import com.example.Restaurant_Management.models.Role;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepositories repositories;

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users user){

        if((repositories.findByEmail(user.getEmail())).isPresent()){
            throw  new IllegalArgumentException("Email đã tồn tại!!!");
        }

        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        user.setRole(Role.CUSTOMER);

        return repositories.save(user);
    }

    public String loginUser(Users users){
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPasswordHash())
            );

            Users loggedInUser = repositories.findByEmail(users.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng"));

            UserResponse response = new UserResponse(
                    loggedInUser.getId(),
                    loggedInUser.getFullname(),
                    jwtService.generateToken(users.getEmail()));

            return "Đăng nhập thành công: \n" + response;
        }catch (BadCredentialsException e){
            return "Đăng nhập thất bại: Sai mật khẩu hoặc email" ;
        }catch (Exception e) {
            return "Đăng nhập thất bại: " + e.getMessage();
        }
    }

    public Users findByUserName(String email){

        Users user = repositories.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tìm thấy"));

        return user;
    }
}
