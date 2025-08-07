package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.CategoryResponse;
import com.example.Restaurant_Management.models.Category;
import com.example.Restaurant_Management.repositories.CategoryRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepositories repositories;

    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> categories = new ArrayList<>();
        List<Category> list = repositories.findAll();
        for (Category category : list) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(category.getId());
            categoryResponse.setName(category.getName());
            categories.add(categoryResponse);
        }
        return categories;
    }

    public CategoryResponse getCategory(int id) {
        CategoryResponse categoryResponse = new CategoryResponse();
        Category category = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());

        return categoryResponse;
    }

    public boolean deleteCategory(int id) {
        Category category = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id not found: " + id));
        category.setDeletedAt(LocalDateTime.now());
        repositories.delete(category);
        return true;
    }

    public CategoryResponse createCategory(CategoryResponse categoryResponse) {
        Category category = new Category();
        category.setName(categoryResponse.getName());
        Category savedCategory = repositories.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());
        response.setCreatedAt(LocalDateTime.now());
        response.setUpdatedAt(LocalDateTime.now());
        return response;
    }

    public void updateCategory(int id, CategoryResponse categoryResponse) {
        Category category = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        if (category.getName() != null && !category.getName().trim().isEmpty()) {
            categoryResponse.setName(category.getName());
        }
        categoryResponse.setUpdatedAt(LocalDateTime.now());
        repositories.save(category);
    }
}
