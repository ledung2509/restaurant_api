package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CheckoutResponse;
import com.example.Restaurant_Management.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class OrderControllers {

    @Autowired
    private OrderService service;

    @PostMapping("/user/checkout")
    public ResponseEntity<?> checkout(){
        service.processOrder();
        return ResponseEntity.ok("Checkout successful");
    }

    @GetMapping("/user/order/view_order")
    public CheckoutResponse viewOrder(){
        return service.viewOrder();
    }
}
