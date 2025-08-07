package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.CartResponse;
import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartControllers {

    //Inject CartService
    //Sử dụng @RequiredArgsConstructor để tự động tạo constructor với các tham số là
    //các trường final, giúp giảm boilerplate code.
    //Điều này giúp dễ dàng quản lý các phụ thuộc và làm cho mã nguồn sạch
    private final CartService service;

    //Thêm sản phẩm vào trong giỏ hàng
    @PostMapping("/customer/cart/add-to-cart")
    public ResponseEntity<Object> addCart(@RequestBody CartItems cartItems, Authentication auth) {

        String email = String.valueOf(auth.getName());
        String userId = String.valueOf(service.getUserId(email));
        service.addCart(userId, cartItems);
        return ResponseEntity.ok("Added to cart successfully: " + cartItems);
    }

    @GetMapping("/customer/cart/view-cart")
    public ResponseEntity<Object> viewCart(Authentication auth) {
        List<CartItems> cartItems = service.getCart();
        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Cart is empty");
        }
        //Tổng số tiền sản phẩm trong giỏ hàng
        double sum = 0;
        for (CartItems cartItem : cartItems) {
            sum += cartItem.getPrice();
        }
        CartResponse resp = new CartResponse(service.getUserId(auth.getName()), cartItems, sum);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/customer/cart/customer-id")
    public ResponseEntity<Object> userId(Authentication auth) {
        int userId = service.getUserId(auth.getName());
        return ResponseEntity.ok(userId);
    }

    @PutMapping("/customer/cart/update-cart/{id}")
    public ResponseEntity<Object> updateCart(@PathVariable int id, @RequestBody CartItems cartItems) {
        if (cartItems.getQuantity() <= 0) {
            service.deleteCart(12);
            return ResponseEntity.ok("Product deleted by quantity <= 0");
        }
        service.updateCart(id, cartItems);
        return ResponseEntity.ok("Updated cart successfully");
    }

    @GetMapping("/customer/cart/detail-cart/{id}")
    public ResponseEntity<Object> getDetailCart(@PathVariable int id) {
        return ResponseEntity.ok(service.getCartItems(id));
    }

    @DeleteMapping("/customer/cart/delete-cart/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable int id) {
        service.deleteCart(id);
        return ResponseEntity.ok("Delete cart successfully");
    }

    @DeleteMapping("/customer/cart/clear-cart")
    public ResponseEntity<Object> clearCart(Authentication auth) {
        String userId = String.valueOf(service.getUserId(auth.getName()));
        service.clearCart(userId);
        return ResponseEntity.ok("Clear cart successfully");
    }
}
