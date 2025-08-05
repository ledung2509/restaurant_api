package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.repositories.CartRepositories;
import com.example.Restaurant_Management.repositories.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepositories repositories;

    private final UserRepositories userRepositories;

    public int getUserId(String email) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();
        Users user = userRepositories.findByEmail(email).orElseThrow();
        return user.getId();
    }

    public void addCart(String userId, CartItems cartItems) {
        //String userId=String.valueOf(userRepositories.findByEmail(email).get().getId()) ;
        repositories.addCart(userId, cartItems);
    }

    public List<CartItems> getCart() {
        return repositories.getCart();
    }

    public void updateCart(int productId, CartItems cartItems) {
        repositories.updateCart(productId, cartItems);
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
