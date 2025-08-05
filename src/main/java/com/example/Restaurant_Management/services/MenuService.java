package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.repositories.MenuItemRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepositories repositories;

    private MenuResponse mapToMenuResponse(MenuItems items) {
        MenuResponse menu = new MenuResponse();
        menu.setMenuId(items.getId());
        menu.setMenuName(items.getName());
        menu.setDescription(items.getDescription());
        menu.setPrice(items.getPrice());
        menu.setCategoryName(items.getCategory().getName());
        menu.setRestaurantName(items.getRestaurant().getName());
        return menu;
    }

    public List<MenuResponse> getMenuItems() {
        List<MenuItems> responses = repositories.findAll();
        List<MenuResponse> menuDTO = new ArrayList<>();
        for (MenuItems items : responses) {
            menuDTO.add(mapToMenuResponse(items));
        }
        return menuDTO;
    }

    public MenuResponse getMenuItem(int id) {
        MenuItems item = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));
        return mapToMenuResponse(item);
    }

    public List<MenuResponse> searchMenuItem(String name) {

        List<MenuResponse> resp = new ArrayList<>();
        List<MenuItems> item = repositories.searchByMenu(name);
        for (MenuItems items : item) {
            resp.add(mapToMenuResponse(items));
        }
        return resp;
    }
}
