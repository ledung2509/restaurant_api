package com.example.Restaurant_Management.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tables {

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

    public enum  Status{
        AVAILABLE, //Bàn trống,có thể đặt
        RESERVED, //Đã được đặt trước
        OCCUPIED, //Đang có khách ngồi
        NEED_CLEANING, //Đang vệ sinh
        OUT_OF_SERVICE //Hỏng cần được sửa chữa
    }
}
