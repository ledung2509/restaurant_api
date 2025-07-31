package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersResponse {

    private String fullName;
    private String email;
    private String phone;
    private String username;
    private String createdAt;
}
