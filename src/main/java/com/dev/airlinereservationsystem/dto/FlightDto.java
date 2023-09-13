package com.dev.airlinereservationsystem.dto;

import com.dev.airlinereservationsystem.entity.Booking;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {
    private String origin;
    private String destination;
    private int availableSeats;
    private Date arrivalTime;
    private Date departureTime;

    @OneToMany(mappedBy = "flight")
    private Set<Booking> bookings = new HashSet<>();
}
