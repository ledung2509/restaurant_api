package com.example.Restaurant_Management.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 15)
    private String phone;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Users manager;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItems> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Tables> tables;

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservations;
}
