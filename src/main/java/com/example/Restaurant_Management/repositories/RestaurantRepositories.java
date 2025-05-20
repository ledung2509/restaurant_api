package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepositories extends JpaRepository<Restaurant, Integer> {

}
