package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.repositories.RestaurantRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepositories repositories;

    public List<Restaurant> findAll(){
        return repositories.findAll();
    }

    public RestaurantResponse findById(int id){
        RestaurantResponse response = new RestaurantResponse();
        Restaurant restaurant = repositories.findById(id).orElseThrow();

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

    public List<RestaurantResponse> searchByName(String name){
        List<RestaurantResponse> resp = new ArrayList<>();
        List<Restaurant> res = repositories.searchByName(name);

        for(Restaurant r : res){
            RestaurantResponse response = new RestaurantResponse();
            response.setId(r.getId());
            response.setName(r.getName());
            response.setAddress(r.getAddress());
            response.setPhone(r.getPhone());
            response.setOpenTime(r.getOpenTime());
            response.setCloseTime(r.getCloseTime());
            response.setDescription(r.getDescription());
            response.setManagerFullName(r.getManager().getFullname());
            resp.add(response);
        }

        return resp;
    }
}
