package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirport(Airport addedAirport);

    Flight findByFlightNumber(String flightNumber);
}
