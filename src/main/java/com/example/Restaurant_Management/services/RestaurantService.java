package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.repositories.RestaurantRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    //Autowired
    private final RestaurantRepositories repositories;

    public List<Restaurant> findAll() {
        return repositories.findAll();
    }

    public RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setPhone(restaurant.getPhone());
        response.setOpenTime(restaurant.getOpenTime());
        response.setCloseTime(restaurant.getCloseTime());
        response.setDescription(restaurant.getDescription());
        response.setManagerFullName(restaurant.getManager().getFullname());
        response.setCreatedAt(LocalDateTime.now());
        response.setUpdatedAt(LocalDateTime.now());
        return response;
    }

    public RestaurantResponse findById(int id) {
        Restaurant restaurant = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        return mapToRestaurantResponse(restaurant);
    }

    public List<RestaurantResponse> searchByName(String name) {
        List<RestaurantResponse> resp = new ArrayList<>();
        List<Restaurant> res = repositories.searchByName(name);
        for (Restaurant r : res) {
            RestaurantResponse response = mapToRestaurantResponse(r);
            resp.add(response);
        }
        return resp;
    }

    public RestaurantResponse create(Restaurant restaurant) {
        Restaurant savedRestaurant = repositories.save(restaurant);
        return mapToRestaurantResponse(savedRestaurant);
    }

    public RestaurantResponse update(Restaurant restaurant) {
        Restaurant existingRestaurant = repositories.findById(restaurant.getId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurant.getId()));

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setPhone(restaurant.getPhone());
        existingRestaurant.setOpenTime(restaurant.getOpenTime());
        existingRestaurant.setCloseTime(restaurant.getCloseTime());
        existingRestaurant.setDescription(restaurant.getDescription());
        existingRestaurant.setManager(restaurant.getManager());

        existingRestaurant.setUpdateAt(LocalDateTime.now());
        Restaurant updatedRestaurant = repositories.save(existingRestaurant);
        return mapToRestaurantResponse(updatedRestaurant);
    }

    public RestaurantResponse delete(int id) {
        Restaurant restaurant = repositories.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        restaurant.setDeletedAt(LocalDateTime.now());
        repositories.delete(restaurant);
        return mapToRestaurantResponse(restaurant);
    }
}
