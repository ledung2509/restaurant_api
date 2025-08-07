package com.example.Restaurant_Management.models;

import com.example.Restaurant_Management.models.entity.CUDEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tables")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tables extends CUDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;

    private String table_number;

    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        AVAILABLE, //Bàn trống,có thể đặt
        RESERVED, //Đã được đặt trước
        OCCUPIED, //Đang có khách ngồi
        NEED_CLEANING, //Đang vệ sinh
        OUT_OF_SERVICE //Hỏng cần được sửa chữa
    }
}
