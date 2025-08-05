package com.example.Restaurant_Management.models;

import com.example.Restaurant_Management.models.entity.CUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users extends CUDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "fullname", nullable = false, length = 100)
    @NotBlank(message = "Fullname not empty")
    private String fullname;

    @Column(name = "email", length = 100)
    @NotBlank(message = "Email not empty")
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "passwordHash", length = 100)
    private String passwordHash;

    @Column(name = "role", nullable = false)
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
                ", role=" + role +
                '}';
    }
}
