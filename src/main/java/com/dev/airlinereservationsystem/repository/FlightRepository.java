package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    int findAvailableSeatsByFlight(Flight flight);
}
