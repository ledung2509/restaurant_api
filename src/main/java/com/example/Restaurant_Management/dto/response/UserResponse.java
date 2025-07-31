package com.example.Restaurant_Management.dto.response;

import lombok.Data;

@Data
public class UserResponse {

    private int id;
    private String fullname;
    private String token;

    public UserResponse(int id, String fullname, String token) {
        this.id = id;
        this.fullname = fullname;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserResponse{" + "\n" +
                "id=" + id + ",\n" +
                "fullname='" + fullname + '\'' + ",\n" +
                "token='" + token + '\'' + '\n' +
                '}';
    }
}
