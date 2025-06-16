package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemRepositories extends JpaRepository<MenuItems, Integer> {

    @Query(value = "SELECT * FROM restaurant_management.menu_items WHERE UPPER(name) LIKE CONCAT('%', UPPER(:name), '%')", nativeQuery = true)
    List<MenuItems> searchByMenu(@Param("name") String name);
}
