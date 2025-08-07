package com.example.Restaurant_Management.models;

import com.example.Restaurant_Management.models.entity.CUDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "category")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends CUDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<MenuItems> menuItems;


}
