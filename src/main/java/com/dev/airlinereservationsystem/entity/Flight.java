package com.dev.airlinereservationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String origin;
    private String destination;
    private int availableSeats;
    private String flightNumber;
    private Date arrivalTime;
    private Date departureTime;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Booking> bookings = new HashSet<>();

    public Flight(String origin, String destination,int availableSeats, Date arrivalTime, Date departureTime, Airport airport) {
        this.origin = origin;
        this.destination = destination;
        this.availableSeats =  availableSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.departureAirport = airport;
    }
    public Flight(String flightNumber, String origin, String destination,int availableSeats, Date arrivalTime, Date departureTime, Airport airport) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats =  availableSeats;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.departureAirport = airport;
    }
}
