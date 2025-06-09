package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CartResponse;
import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.repositories.UserRepositories;
import com.example.Restaurant_Management.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartControllers {

    @Autowired
    private CartService service;

    //Thêm sản phẩm vào trong giỏ hàng
    @PostMapping("/customer/cart/add-to-cart")
    public ResponseEntity<?> addCart(@RequestBody CartItems cartItems, Authentication auth) {

        String email = String.valueOf(auth.getName());
        String userId = String.valueOf(service.getUserId(email));
        service.addCart(userId, cartItems);
        return ResponseEntity.ok("Added to cart successfully: " + cartItems);
    }

    @GetMapping("/customer/cart/view-cart")
    public ResponseEntity<?> viewCart(Authentication auth) {
        List<CartItems> cartItems = service.getCart();
        if (cartItems.size() == 0) {
            return ResponseEntity.ok("Cart is empty");
        }
        //Tổng số tiền sản phẩm trong giỏ hàng
        double sum = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            sum += cartItems.get(i).getPrice();
        }
        CartResponse resp = new CartResponse(service.getUserId(auth.getName()), cartItems, sum);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/customer/cart/customer-id")
    public ResponseEntity<?> userId(Authentication auth) {
        int userdId = service.getUserId(auth.getName());
        return ResponseEntity.ok(userdId);
    }

    @PutMapping("/customer/cart/update-cart/{id}")
    public ResponseEntity<?> updateCart(@PathVariable int id,@RequestBody CartItems cartItems) {
        if (cartItems.getQuantity() <= 0) {
            service.deleteCart(12);
            return ResponseEntity.ok("Product deleted by quantity <= 0");
        }
        service.updateCart(id,cartItems);
        return ResponseEntity.ok("Updated cart successfully");
    }

    @GetMapping("/customer/cart/detail-cart/{id}")
    public ResponseEntity<?> getDetailCart(@PathVariable int id) {
        return ResponseEntity.ok(service.getCartItems(id));
    }

    @DeleteMapping("/customer/cart/delete-cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id) {
        service.deleteCart(id);
        return ResponseEntity.ok("Delete cart successfully");
    }

    @DeleteMapping("/customer/cart/clear-cart")
    public ResponseEntity<?> clearCart(Authentication auth) {
        String userId = String.valueOf(service.getUserId(auth.getName()));
        service.clearCart(userId);
        return ResponseEntity.ok("Clear cart successfully");
    }
}
