package com.example.Restaurant_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int id;
    private String fullname;
    private String token;

    @Override
    public String toString() {
        return "UserResponse{" + "\n" +
                "id=" + id + ",\n" +
                "fullname='" + fullname + '\'' + ",\n" +
                "token='" + token + '\'' + '\n' +
                '}';
    }
}
