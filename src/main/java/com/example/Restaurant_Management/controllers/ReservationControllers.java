package com.example.Restaurant_Management.controllers;

import com.example.Restaurant_Management.dto.request.ReservationRequest;
import com.example.Restaurant_Management.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReservationControllers {

    private final ReservationService service;

    @PostMapping("/customer/reservation/booking")
    public ResponseEntity<Object> bookingTable(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ReservationRequest request) {

        String rs = service.BookingTable(userDetails.getUsername(), request);

        return ResponseEntity.ok(rs);
    }

}
