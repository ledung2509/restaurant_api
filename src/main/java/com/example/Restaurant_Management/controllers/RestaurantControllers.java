package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
// @RequiredArgsConstructor sẽ tự động tạo constructor với các tham số là các bean được @Autowired
// Điều này giúp giảm boilerplate code và làm cho mã nguồn sạch hơn
// Lưu ý: @RequiredArgsConstructor chỉ hoạt động với các trường final hoặc @Autowired
// Nếu bạn không sử dụng @Autowired, bạn có thể bỏ qua @RequiredArgsConstructor và
// chỉ cần sử dụng @Autowired trên các trường cần thiết
// Trong trường hợp này, chúng ta sử dụng @Autowired để inject RestaurantService vào trong controller
// và @RequiredArgsConstructor để tự động tạo constructor với tham số là RestaurantService
public class RestaurantControllers {

    private final RestaurantService service;

    @GetMapping("/manager/restaurant/list")
    @PreAuthorize("hasRole('MANAGER')")
    public List<RestaurantResponse> getAllRestaurants(){
        List<Restaurant> restaurants= service.findAll();
        List<RestaurantResponse> restaurantDTOs = new ArrayList<>();
        for (Restaurant r : restaurants ){
            restaurantDTOs.add(service.mapToRestaurantResponse(r));
        }
        return restaurantDTOs;
    }

    @GetMapping("/manager/restaurant/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public RestaurantResponse getRestaurantById(@PathVariable int id){
        return service.findById(id);
    }

    @GetMapping("/guest/restaurant/search")
    public List<RestaurantResponse> searchRestaurantByName(@RequestParam("name") String name){
        return service.searchByName(name);
    }

    @PostMapping("/manager/restaurant/create")
    @PreAuthorize("hasRole('MANAGER')")
    public RestaurantResponse createRestaurant(Restaurant restaurant) {
        return service.create(restaurant);
    }

    @PutMapping("/manager/restaurant/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public RestaurantResponse updateRestaurant(@PathVariable int id, Restaurant restaurant) {
        restaurant.setId(id);
        return service.update(restaurant);
    }

    @DeleteMapping("/manager/restaurant/delete/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public String deleteRestaurant(@PathVariable int id) {
        service.delete(id);
        return "Restaurant with id: " + id + " has been deleted successfully.";
    }
}
