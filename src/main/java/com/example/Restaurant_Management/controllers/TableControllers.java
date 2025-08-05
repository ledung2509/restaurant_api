package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.response.TableResponse;
import com.example.Restaurant_Management.models.Tables;
import com.example.Restaurant_Management.services.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TableControllers {

    private final TableService service;

    private TableResponse mapToTableResponse(Tables table) {
        TableResponse dto = new TableResponse();

        dto.setId(table.getId());
        dto.setTable_number(table.getTable_number());
        dto.setCapacity(table.getCapacity());

        if (table.getStatus() == Tables.Status.AVAILABLE) {
            dto.setStatus("Bàn đang trống");
        } else if (table.getStatus() == Tables.Status.RESERVED) {
            dto.setStatus("Bàn đã có người đặt");
        } else if (table.getStatus() == Tables.Status.NEED_CLEANING){
            dto.setStatus("Bàn đang được vệ sinh");
        } else if (table.getStatus() == Tables.Status.OUT_OF_SERVICE){
            dto.setStatus("Bàn đang sửa chữa");
        } else if (table.getStatus() == Tables.Status.OCCUPIED) {
            dto.setStatus("Bàn đang có khách ngồi");
        }

        if (table.getRestaurant() != null){
            dto.setRestaurantName(table.getRestaurant().getName());
        }
        return dto;
    }

    @GetMapping("/manager/table/list")
    @PreAuthorize("hasRole('MANAGER')")
    public List<TableResponse> allTables() {

        List<Tables> tablesList = service.findAll();

        List<TableResponse> tableDTOs = new ArrayList<>();

        for (Tables tables : tablesList) {
            tableDTOs.add(mapToTableResponse(tables));
        }
        return tableDTOs;
    }

    @GetMapping("/manager/table/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public TableResponse findById(@PathVariable int id) {
        Tables tables = service.findById(id);
        return mapToTableResponse(tables);
    }
}
