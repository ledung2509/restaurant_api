package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Repository
public class OrderRepositories {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MenuItemRepositories menuItemRepositories;

    @Autowired
    private UserRepositories userRepositories;

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositories(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getUserById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Users users = userRepositories.findByEmail(email)
                .orElseThrow();

        int userId = users.getId();
        return userId;
    }

    @Transactional
    public void checkOut(){

        //Thêm vào bảng Cart
        String sql = "insert into restaurant_management.carts(created_at,status,user_id) " +
                "values(?,?,?)";

        //Lấy thông tin từ trong redis
        HashOperations<String, String, CartItems> ops = redisTemplate.opsForHash();

        Map<String, CartItems> map = ops.entries(String.valueOf(getUserById()));

        Carts carts = new Carts();
        carts.setUser(new Users(getUserById()));
        int user_id = carts.getUser().getId();

        carts.setStatus(Carts.Status.completed);

        String status = carts.getStatus().name();

        //Set formate ngày tháng năm
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(formatter);

        carts.setCreatedAt(date);

        try{
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, carts.getCreatedAt());
            ps.setString(2, status);
            ps.setInt(3, user_id);

            ps.executeUpdate();

            //Lấy id cart trong bảng cart
            ResultSet rs = ps.getGeneratedKeys();
            int cart_id = 0;

            if (rs.next()) {
                cart_id = rs.getInt(1);
            }

            carts.setId(cart_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Thêm dữ liệu vào bảng cart_detail
        String sql1 ="INSERT INTO restaurant_management.cart_detail(price,quantity,cart_id,product_id)\n" +
                "VALUES(?,?,?,?)";

        CartDetails details = new CartDetails();

        for (CartItems cartItems : map.values()) {

            MenuItems item = menuItemRepositories.findById(cartItems.getProductId()).orElseThrow();

            if(item != null){
                details.setCart(carts);
                details.setPrice(cartItems.getPrice());
                details.setQuantity(cartItems.getQuantity());
                details.setItem(item);

                try{
                    Connection connection = jdbcTemplate.getDataSource().getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql1);

                    ps.setDouble(1, details.getPrice());
                    ps.setInt(2, details.getQuantity());
                    ps.setInt(3, carts.getId());
                    ps.setInt(4,details.getItem().getId());

                    ps.executeUpdate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //jdbcTemplate.update(sql, user_id, status,carts.getCreatedAt());

        redisTemplate.delete(String.valueOf(getUserById()));
    }

    public void viewCheckout(){
        String sql = "select * from restaurant_management.carts where user_id=?";
        
    }
}
