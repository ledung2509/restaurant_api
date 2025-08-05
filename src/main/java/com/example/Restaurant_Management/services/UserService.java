package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    //Autowired
    private final UserRepositories userRepositories;

    //Autowired
    private final JwtService jwtService;

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

    public Users updateUserProfile(Users users) {
        Users existingUser = userRepositories.findById(users.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng"));

        if (existingUser == null) {
            throw new UsernameNotFoundException("Người dùng không tồn tại");
        }
        if (users.getFullname() != null && !users.getFullname().trim().isEmpty()) {
            existingUser.setFullname(users.getFullname());
        }
        if (users.getEmail() != null && !users.getEmail().trim().isEmpty()) {
            existingUser.setEmail(users.getEmail());
        }
        if (users.getPhone() != null && !users.getPhone().trim().isEmpty()) {
            existingUser.setPhone(users.getPhone());
        }
        if (users.getUsername() != null && !users.getUsername().trim().isEmpty()) {
            existingUser.setUsername(users.getUsername());
        }
        existingUser.setUpdateAt(LocalDateTime.now());

        return userRepositories.save(existingUser);
    }

    
}
