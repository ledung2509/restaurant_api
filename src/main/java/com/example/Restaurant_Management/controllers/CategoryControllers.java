package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.models.Category;
import com.example.Restaurant_Management.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryControllers {

    @Autowired
    private CategoryService service;

    @GetMapping("/list")
    public List<Category> findAll() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable int id) {
        return service.getCategory(id);
    }
}
