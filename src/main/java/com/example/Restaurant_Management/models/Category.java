package com.example.Restaurant_Management.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<MenuItems> menuItems;


}
