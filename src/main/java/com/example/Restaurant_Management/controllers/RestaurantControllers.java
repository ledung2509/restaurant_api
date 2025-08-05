package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.RestaurantResponse;
import com.example.Restaurant_Management.models.Restaurant;
import com.example.Restaurant_Management.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        RestaurantResponse dto = new RestaurantResponse();
        for (Restaurant r : restaurants ){
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setAddress(r.getAddress());
            dto.setPhone(r.getPhone());
            dto.setOpenTime(r.getOpenTime());
            dto.setCloseTime(r.getCloseTime());
            dto.setDescription(r.getDescription());

            if (r.getManager() != null) {
                dto.setManagerFullName(r.getManager().getFullname());
                // Thêm bất kỳ thông tin nào khác mà bạn muốn từ người quản lý
            }

            restaurantDTOs.add(dto);
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
}
