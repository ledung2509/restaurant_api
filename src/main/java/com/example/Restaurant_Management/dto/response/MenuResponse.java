package com.example.Restaurant_Management.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuResponse {

    private int menuId;
    private String menuName;
    private String description;
    private double price;
    private String categoryName;
    private String restaurantName;
}
