package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuControllers {

    @Autowired
    private MenuService service;

    @GetMapping("/guest/menu_item/list")
    public List<MenuResponse> MenuCotroller() {
        return service.getMenuItems();
    }

    @GetMapping("/guest/menu_item/{id}")
    public MenuResponse findById(@PathVariable int id) {
        return service.getMenuItem(id);
    }

    @GetMapping("/guest/menu_item/search")
    public List<MenuResponse> searchByNaeme(@RequestParam("name") String item) {
        List<MenuResponse> menu = service.searchMenuItem(item);
        return menu;
    }

}