package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.Category;
import com.example.Restaurant_Management.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepositories repositories;

    public List<Category> getAllCategories() {
        return repositories.findAll();
    }

    public Category getCategory(int id) {
        return repositories.findById(id).get();
    }
}
