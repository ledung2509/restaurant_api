package com.example.Restaurant_Management.dto.response;

import com.example.Restaurant_Management.models.Restaurant;

import java.util.List;

public class CheckoutResponse {

    private int user_id;
    private List<OrderResponse> orders;
    private double total_price;
}
