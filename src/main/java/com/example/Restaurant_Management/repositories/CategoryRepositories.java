package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositories extends JpaRepository<Category, Integer> {
}
