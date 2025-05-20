package com.example.Restaurant_Management.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

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
