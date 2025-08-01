package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckoutResponse {

    private int user_id;
    private List<OrderResponse> orders;
    private double total_price;
}
