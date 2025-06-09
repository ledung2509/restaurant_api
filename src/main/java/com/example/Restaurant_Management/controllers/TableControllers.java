package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.TableResponse;
import com.example.Restaurant_Management.models.Tables;
import com.example.Restaurant_Management.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TableControllers {

    @Autowired
    private TableService service;

    @GetMapping("/manager/table/list")
    public List<TableResponse> allTables() {

        List<Tables> tablesList = service.findAll();

        List<TableResponse> tableDTOs = new ArrayList<>();

        for (Tables tables : tablesList) {

            TableResponse dto = new TableResponse();

            dto.setId(tables.getId());
            dto.setTable_number(tables.getTable_number());
            dto.setCapacity(tables.getCapacity());

            if (tables.getStatus() == Tables.Status.AVAILABLE) {
                dto.setStatus("Bàn đang trống");
            } else if (tables.getStatus() == Tables.Status.RESERVED) {
                dto.setStatus("Bàn đã có người đặt");
            } else if (tables.getStatus() == Tables.Status.NEED_CLEANING){
                dto.setStatus("Bàn đang được vệ sinh");
            } else if (tables.getStatus() == Tables.Status.OUT_OF_SERVICE){
                dto.setStatus("Bàn đang sửa chữa");
            } else if (tables.getStatus() == Tables.Status.OCCUPIED) {
                dto.setStatus("Bàn đang có khách ngồi");
            }

            if (tables.getRestaurant() != null){
                dto.setRestaurantName(tables.getRestaurant().getName());
            }

            tableDTOs.add(dto);
        }
        return tableDTOs;
    }

    @GetMapping("/manager/table/{id}")
    public TableResponse findById(@PathVariable int id) {

        Tables tables = service.findById(id);
        TableResponse dto = new TableResponse();
        dto.setId(tables.getId());
        dto.setTable_number(tables.getTable_number());
        dto.setCapacity(tables.getCapacity());
        if (tables.getStatus() == Tables.Status.AVAILABLE) {
            dto.setStatus("Bàn đang trống");
        } else if (tables.getStatus() == Tables.Status.RESERVED) {
            dto.setStatus("Bàn đã có người đặt");
        } else if (tables.getStatus() == Tables.Status.NEED_CLEANING){
            dto.setStatus("Bàn đang được vệ sinh");
        } else if (tables.getStatus() == Tables.Status.OUT_OF_SERVICE){
            dto.setStatus("Bàn đang sửa chữa");
        } else if (tables.getStatus() == Tables.Status.OCCUPIED) {
            dto.setStatus("Bàn đang có khách ngồi");
        }

        if (tables.getRestaurant() != null){
            dto.setRestaurantName(tables.getRestaurant().getName());
        }

        return dto;
    }
}
