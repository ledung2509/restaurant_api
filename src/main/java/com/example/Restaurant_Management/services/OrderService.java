package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.dto.response.CheckoutResponse;
import com.example.Restaurant_Management.repositories.OrderRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositories orderRepositories;

    public void processOrder() {
        orderRepositories.checkOut();
    }

    public CheckoutResponse viewOrder() {
        return orderRepositories.viewCheckout();
    }
}
