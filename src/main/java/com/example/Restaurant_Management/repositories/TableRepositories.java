package com.example.Restaurant_Management.repositories;

import com.example.Restaurant_Management.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepositories extends JpaRepository<Tables,Integer> {

    Optional<Tables> findById(int id);

    List<Tables> findByStatus(Tables.Status status);
}
