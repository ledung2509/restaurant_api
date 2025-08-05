package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.CategoryResponse;
import com.example.Restaurant_Management.models.Category;
import com.example.Restaurant_Management.repositories.CategoryRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepositories repositories;

    //private CategoryResponse response = new CategoryResponse();

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
}
