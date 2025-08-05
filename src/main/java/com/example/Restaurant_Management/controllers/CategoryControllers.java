package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CategoryResponse;
import com.example.Restaurant_Management.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryControllers {

    private final CategoryService service;

    @GetMapping("/guest/category/list")
    public List<CategoryResponse> findAll() {
        return service.getAllCategories();
    }

    @GetMapping("/guest/category/{id}")
    public CategoryResponse findById(@PathVariable int id) {
        return service.getCategory(id);
    }
}
