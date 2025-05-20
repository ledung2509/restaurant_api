package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepositories extends JpaRepository<MenuItems, Integer> {
}
