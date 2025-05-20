package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {

    private int menuId;
    private String menuName;
    private String description;
    private double price;
    private String status;
    private String categoryName;
    private String restaurantName;
}
