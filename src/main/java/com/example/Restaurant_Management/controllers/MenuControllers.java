package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuControllers {

    @Autowired
    private MenuService service;

    @GetMapping("/user/menu_item/list")
    public List<MenuItems> MenuCotroller(){
        return service.getMenuItems();
    }

    @GetMapping("/user/menu_item/{id}")
    public MenuItems findById(@PathVariable int id) {
        return service.getMenuItem(id);
    }
}
