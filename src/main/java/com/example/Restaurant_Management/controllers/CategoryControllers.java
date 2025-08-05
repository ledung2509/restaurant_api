package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CategoryResponse;
import com.example.Restaurant_Management.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/admin/category/create")
    @Transactional
    public CategoryResponse createCategory(CategoryResponse categoryResponse) {
        return service.createCategory(categoryResponse);
    }

    @DeleteMapping("/admin/category/delete/{id}")
    @Transactional
    public String deleteCategory(@PathVariable int id) {
        if(id <= 0 || id > Integer.MAX_VALUE ) {
            return "Invalid category ID";
        }
        if (service.deleteCategory(id)) {
            return "Category deleted successfully";
        } else {
            return "Category not found or could not be deleted";
        }
    }
}
