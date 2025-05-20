package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantControllers {

    @Autowired
    private RestaurantService service;

    @GetMapping("/user/restaurant/list")
    public List<RestaurantResponse> getAllRestaurants(){

        List<Restaurant> restaurants= service.findAll();
        List<RestaurantResponse> restaurantDTOs = new ArrayList<>();

        for (Restaurant r : restaurants ){

            RestaurantResponse dto = new RestaurantResponse();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setAddress(r.getAddress());
            dto.setPhone(r.getPhone());
            dto.setOpenTime(r.getOpenTime());
            dto.setCloseTime(r.getCloseTime());
            dto.setDescription(r.getDescription());

            if (r.getManager() != null) {
                dto.setManagerFullName(r.getManager().getFullname());
                // Thêm bất kỳ thông tin nào khác mà bạn muốn từ người quản lý
            }

            restaurantDTOs.add(dto);
        }

        return restaurantDTOs;
    }
}
