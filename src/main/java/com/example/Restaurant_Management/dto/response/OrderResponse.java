package com.example.Restaurant_Management.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private String productName;
    private int quantity;
    private double price;
    private String createdAt;
}
