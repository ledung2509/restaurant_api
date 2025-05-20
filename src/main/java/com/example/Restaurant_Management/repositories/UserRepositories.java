package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//reflect api
@Repository
public interface UserRepositories extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailAndPasswordHash(String email, String passwordHash);
}
