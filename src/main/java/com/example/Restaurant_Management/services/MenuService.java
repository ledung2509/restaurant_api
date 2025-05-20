package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.repositories.MenuItemRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuItemRepositories repositories;


    public List<MenuItems> getMenuItems() {
        return repositories.findAll();
    }

    public MenuItems getMenuItem(int id) {
        return repositories.findById(id).get();
    }
}
