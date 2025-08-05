package com.example.Restaurant_Management.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class ReservationRequest {

    private int capacity;
    private int restaurantID;
    private String reservationDate;
    private String reservationTime;
}
