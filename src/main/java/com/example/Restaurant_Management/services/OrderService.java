package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.CheckoutResponse;
import com.example.Restaurant_Management.repositories.OrderDetailRepositories;
import com.example.Restaurant_Management.repositories.OrderRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepositories orderRepositories;

    public void processOrder(){
        orderRepositories.checkOut();
    }

    public CheckoutResponse viewOrder(){
        return orderRepositories.viewCheckout();
    }
}
