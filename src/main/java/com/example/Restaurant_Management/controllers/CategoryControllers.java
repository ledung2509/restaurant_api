package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CategoryResponse;
import com.example.Restaurant_Management.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(CategoryResponse categoryResponse) {
        return service.createCategory(categoryResponse);
    }

    @PutMapping("/admin/category/update/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateCategory(@PathVariable int id, CategoryResponse categoryResponse) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid category ID");
        }
        CategoryResponse existingCategory = service.getCategory(id);
        if (existingCategory == null) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        if (categoryResponse.getName() != null && !categoryResponse.getName().trim().isEmpty()) {
            existingCategory.setName(categoryResponse.getName());
        }
        existingCategory.setUpdatedAt(LocalDateTime.now());
        service.updateCategory(id, existingCategory);
        return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
    }

    @DeleteMapping("/admin/category/delete/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid category ID");
        }
        if (service.deleteCategory(id)) {
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found or could not be deleted");
        }
    }
}
