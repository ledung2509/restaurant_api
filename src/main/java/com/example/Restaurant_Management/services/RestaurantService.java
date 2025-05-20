package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.repositories.RestaurantRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepositories repositories;

    public List<Restaurant> findAll(){
        return repositories.findAll();
    }
}
