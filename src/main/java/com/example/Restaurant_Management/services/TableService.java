package com.example.Restaurant_Management.services;

import com.example.Restaurant_Management.models.Tables;
import com.example.Restaurant_Management.repositories.TableRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepositories repositories;

    public List<Tables> findAll(){
        return repositories.findAll();
    }

    public Tables findById(int id){
        return repositories.findById(id).get();
    }
}
