package com.dev.airlinereservationsystem.entity;

import com.dev.airlinereservationsystem.util.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bookingNumber;
    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne // Many bookings can be associated with one departure airport
    @JoinColumn(name = "departure_airport_id") // This column will be created in the Booking table
    private Airport departureAirport;
}
