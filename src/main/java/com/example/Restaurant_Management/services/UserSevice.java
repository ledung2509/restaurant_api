package com.example.Restaurant_Management.services;


import com.example.Restaurant_Management.dto.response.UsersResponse;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private JwtService jwtService;

    public String getUserDetails(String token) {

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = jwtService.extractUserName(token);

        Users users = userRepositories.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Người dùng không tồn tại"));

        return users.toString();
    }

    public Users getUsersProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        String email = auth.getName();
        return userRepositories.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));
    }
}
