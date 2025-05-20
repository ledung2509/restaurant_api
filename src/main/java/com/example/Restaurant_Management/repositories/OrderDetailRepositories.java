package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepositories extends JpaRepository<CartDetails,Integer> {
}
