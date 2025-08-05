package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuControllers {

    private final MenuService service;

    @GetMapping("/guest/menu_item/list")
    public List<MenuResponse> MenuController() {
        return service.getMenuItems();
    }

    @GetMapping("/guest/menu_item/{id}")
    public MenuResponse findById(@PathVariable int id) {
        return service.getMenuItem(id);
    }

    @GetMapping("/guest/menu_item/search")
    public List<MenuResponse> searchByName(@RequestParam("name") String item) {
        return service.searchMenuItem(item);
    }

}