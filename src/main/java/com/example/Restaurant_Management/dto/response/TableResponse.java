package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableResponse {

    private int id;
    private String table_number;
    private int capacity;
    private String status;
    private String restaurantName;
}
