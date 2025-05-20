package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.CartRepositories;
import com.example.Restaurant_Management.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartRepositories repositories;

    @Autowired
    private UserRepositories userRepositories;

    public int getUserId(String email) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        Users user = userRepositories.findByEmail(email).orElseThrow();
        int userId = user.getId();
        return userId;
    }

    public void addCart(String userId, CartItems cartItems) {
        //String userId=String.valueOf(userRepositories.findByEmail(email).get().getId()) ;
        repositories.addCart(userId, cartItems);
    }

    public List<CartItems> getCart() {
        return repositories.getCart();
    }

    public void updateCart(int productId,CartItems cartItems) {
        repositories.updateCart(productId,cartItems);
    }

    public void deleteCart(int productId) {
        repositories.deleteCart(productId);
    }

    public CartItems getCartItems(int id) {
        return repositories.detailCart(id);
    }

    public void clearCart(String userId) {
        repositories.clearCart(userId);
    }
}
