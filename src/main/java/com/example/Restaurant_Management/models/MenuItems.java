package com.example.Restaurant_Management.models;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.List;

@Table(name = "menu_items")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int id;

    private String name;

    private String description;

    private float price;

    private  boolean isAvaiable;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<CartDetails> details;

    @Column(name = "image_url")
    private String imageUrl;

    public MenuItems(int id) {
        this.id = id;
    }
}
