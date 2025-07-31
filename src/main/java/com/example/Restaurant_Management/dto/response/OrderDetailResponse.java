package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailResponse {

    private String productName;
    private int quantity;
    private double price;
    private String status;
    private String createdAt;
}
