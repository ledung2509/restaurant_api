package com.example.Restaurant_Management.models;

import lombok.*;

import java.io.Serializable;


//Entity dùng để lưu thông tin giỏ hàng
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems implements Serializable {

    //private int cartId;
    private int productId;
    private int quantity;
    private double price;
    private String productName;

    public CartItems(int quantity) {
        this.quantity = quantity;
    }

    public CartItems(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
