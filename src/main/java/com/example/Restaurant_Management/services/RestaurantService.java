package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.repositories.RestaurantRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setPhone(restaurant.getPhone());
        response.setOpenTime(restaurant.getOpenTime());
        response.setCloseTime(restaurant.getCloseTime());
        response.setDescription(restaurant.getDescription());
        response.setManagerFullName(restaurant.getManager().getFullname());
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
}
