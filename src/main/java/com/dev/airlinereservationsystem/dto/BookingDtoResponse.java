package com.dev.airlinereservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoResponse {
    private String airportCode;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date arrivalTime;
    private Date departureTime;
    private Date bookingDate;
    private String bookingNumber;
    private String status;
}
