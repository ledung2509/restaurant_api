package com.example.Restaurant_Management.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "passwordHash", length = 100)
    private String passwordHash;

    @CreationTimestamp //Lấy thời gian hiện tại
    @Column(name = "createdAt", updatable = false)
    private Date createdAt;

    private Date updateAt;

    @Column(name = "role",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "manager")
    private List<Restaurant> restaurantList;

    @OneToMany(mappedBy = "users")
    private List<Reservation> reservation;

    @OneToMany(mappedBy = "user")
    private List<Carts> cartsList;

    public Users(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }
}
