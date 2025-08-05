package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.Tables;
import com.example.Restaurant_Management.repositories.TableRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepositories repositories;

    public List<Tables> findAll(){
        return repositories.findAll();
    }

    public Tables findById(int id){
        return repositories.findById(id).get();
    }
}
