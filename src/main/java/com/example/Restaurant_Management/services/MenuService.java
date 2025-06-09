package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.repositories.MenuItemRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuItemRepositories repositories;


    public List<MenuResponse> getMenuItems() {


        List<MenuItems> responses = repositories.findAll();
        List<MenuResponse> menuDTO = new ArrayList<>();
        for (MenuItems items : responses){
            MenuResponse menu = new MenuResponse();

            menu.setMenuId(items.getId());
            menu.setMenuName(items.getName());
            menu.setDescription(items.getDescription());
            menu.setPrice(items.getPrice());
            menu.setCategoryName(items.getCategory().getName());
            menu.setRestaurantName(items.getRestaurant().getName());

            menuDTO.add(menu);
        }

        return menuDTO;
    }

    public MenuResponse getMenuItem(int id) {

        MenuItems item = repositories.findById(id).get();
        MenuResponse menu = new MenuResponse();

        menu.setMenuId(item.getId());
        menu.setMenuName(item.getName());
        menu.setDescription(item.getDescription());
        menu.setPrice(item.getPrice());
        menu.setCategoryName(item.getCategory().getName());
        menu.setRestaurantName(item.getRestaurant().getName());

        return menu;
    }
}
