package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepositories extends JpaRepository<Restaurant, Integer> {

    @Query(value = "SELECT * FROM restaurant_management.restaurant WHERE UPPER(name) LIKE CONCAT('%', UPPER(:name), '%')", nativeQuery = true)
    List<Restaurant> searchByName(@Param("name") String name);
}
