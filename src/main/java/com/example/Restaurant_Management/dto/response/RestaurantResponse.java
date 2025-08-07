package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String openTime;
    private String closeTime;
    private String description;
    private String managerFullName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
