package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.dto.response.MenuResponse;
import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.models.Users;
import com.example.Restaurant_Management.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.round;

@Repository
public class CartRepositories {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private MenuService menuService;

    public void addCart(String userId, CartItems cart) {

        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();

        userId = getUserById();

        //Lấy thông tin sản phẩm trong menu
        MenuResponse menu = menuService.getMenuItem(cart.getProductId());

        //Lấy thông tin sản phẩm có trong giỏ hàng
        CartItems itemsExsisting = ops.get(userId, String.valueOf(cart.getProductId()));

        double totalPrice = Math.round(menu.getPrice() * cart.getQuantity() * 100.0) / 100.0;

        String productName = menu.getMenuName();

        //Chưa có thông tin ở trong giỏ hàng
        if (itemsExsisting == null) {
            cart.setPrice(totalPrice);
            cart.setProductName(productName);

            cart = new CartItems(cart.getProductId(), cart.getQuantity(), cart.getPrice(), cart.getProductName());

            ops.put(userId, String.valueOf(cart.getProductId()), cart);
        }
        //Thông tin đó đã tồn tại trong giỏ hàng
        else {
            if (itemsExsisting.getProductId() == cart.getProductId()) {
                int quantity = itemsExsisting.getQuantity() + cart.getQuantity();


                itemsExsisting.setQuantity(quantity);
                itemsExsisting.setPrice(Math.round(quantity * menu.getPrice() * 100.0) / 100.0);
                //itemsExsisting.setProductName(productName);

                itemsExsisting = new CartItems(cart.getProductId(),
                        itemsExsisting.getQuantity(),
                        itemsExsisting.getPrice(), itemsExsisting.getProductName());

                ops.put(userId, String.valueOf(cart.getProductId()), itemsExsisting);
            }
        }
    }

    //Lấy thông tin người dùng
    public String getUserById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Users users = userRepositories.findByEmail(email)
                .orElseThrow();

        String userId = String.valueOf(users.getId());
        return userId;
    }

    //Xem thông tin giỏ hàng
    public List<CartItems> getCart() {
        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();

        Map<String, CartItems> cartItems = ops.entries(getUserById());

        return cartItems.values().stream().collect(Collectors.toList());
    }

    //Cập nhật giỏ hàng
    public void updateCart(int productId, CartItems cart) {
        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();

        CartItems cartExsisting = ops.get(getUserById(), String.valueOf(productId));

        MenuResponse menu = menuService.getMenuItem(productId);

        double totalPrice = Math.round(menu.getPrice() * cart.getQuantity() * 100.0) / 100.0;

        //Cập nhật thông tin giỏ hàng
        cartExsisting.setProductId(productId);
        cartExsisting.setProductName(menu.getMenuName());
        cartExsisting.setQuantity(cart.getQuantity());
        cartExsisting.setPrice(totalPrice);

        ops.put(getUserById(), String.valueOf(productId), cartExsisting);

    }

    //Xóa thông tin trong giỏ hàng
    public void deleteCart(int productId) {
        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();

        Map<String, CartItems> cartItems = ops.entries(getUserById());

        for (CartItems items : cartItems.values()) {
            if (items.getProductId() == productId) {
                //Xóa sản phẩm ra khỏi redis
                ops.delete(getUserById(), String.valueOf(productId));
                break;
            }
        }
    }

    //Lấy thông tin chi tiết của 1 đơn hàng
    public CartItems detailCart(int id) {
        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();
        Map<String, CartItems> cartItems = ops.entries(getUserById());
        for (CartItems items : cartItems.values()) {
            if (items.getProductId() == id) {
                return items;
            }
        }
        return null;
    }


    //Xóa sạch thông tin giỏ hàng
    public void clearCart(String userId) {
        redisTemplate.delete(userId);
    }
}
