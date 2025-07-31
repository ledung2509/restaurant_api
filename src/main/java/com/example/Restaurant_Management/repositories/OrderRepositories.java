package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.dto.response.CheckoutResponse;
import com.example.Restaurant_Management.dto.response.OrderDetailResponse;
import com.example.Restaurant_Management.dto.response.OrderResponse;
import com.example.Restaurant_Management.models.CartDetails;
import com.example.Restaurant_Management.models.CartItems;
import com.example.Restaurant_Management.models.Carts;
import com.example.Restaurant_Management.models.MenuItems;
import com.example.Restaurant_Management.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;
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

        carts.setStatus(Carts.Status.active);

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

    public void viewOrderCustomer(){

        List<OrderDetailResponse> responses = new ArrayList<>();

        String sql = "select a.user_id,p.name,b.quantity,b.price,a.status,a.created_at from \n" +
                "restaurant_management.carts as a inner join restaurant_management.cart_detail as b \n" +
                "on a.id = b.cart_id inner join restaurant_management.menu_items as p\n" +
                "on b.product_id = p.menu_id;";
        try{

            Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                OrderDetailResponse orderDetailResponse = new OrderDetailResponse();

                responses.add(orderDetailResponse);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CheckoutResponse viewCheckout(){

        List<OrderResponse> orders = new ArrayList<>();

        String sql = "select a.user_id,p.name,b.quantity,b.price,a.created_at from \n" +
                "restaurant_management.carts as a inner join restaurant_management.cart_detail as b \n" +
                "on a.id = b.cart_id inner join restaurant_management.menu_items as p\n" +
                "on b.product_id = p.menu_id\n" +
                "where a.user_id=?";

        try{
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, getUserById());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderResponse order = new OrderResponse();
                order.setProductName(rs.getString("p.name"));
                order.setQuantity(rs.getInt("b.quantity"));
                order.setPrice(rs.getDouble("b.price"));
                order.setCreatedAt(rs.getString("a.created_at"));
                orders.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CheckoutResponse response = new CheckoutResponse();
        response.setOrders(orders);
        double total_price = 0;
        for (OrderResponse order : orders) {
            total_price += order.getPrice();
        }
        response.setTotal_price(total_price);
        response.setUser_id(getUserById());

        return response;
    }
}
