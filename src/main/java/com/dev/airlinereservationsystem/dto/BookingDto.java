package com.dev.airlinereservationsystem.dto;

import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String bookingNumber;
    private Date bookingDate;
    private int seatNumber;
    private User user;
    private Flight flight;
}
