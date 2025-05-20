package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Reservation;
import com.example.Restaurant_Management.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepositories extends JpaRepository<Reservation,Integer> {

    Reservation findByTable(Tables tables);
}
