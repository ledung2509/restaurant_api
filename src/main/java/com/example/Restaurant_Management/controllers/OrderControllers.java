package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CheckoutResponse;
import com.example.Restaurant_Management.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderControllers {

    private final OrderService service;

    @PostMapping("/customer/order/checkout")
    public ResponseEntity<Object> checkout() {
        service.processOrder();
        return ResponseEntity.ok("Checkout successful");
    }

    @GetMapping("/customer/order/view_order")
    public CheckoutResponse viewOrder() {
        return service.viewOrder();
    }

}
