package com.example.Restaurant_Management.dto.response;

import com.example.Restaurant_Management.models.CartItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private int userId;
    private List<CartItems> cartItems;
    private double totalPrice;
}
