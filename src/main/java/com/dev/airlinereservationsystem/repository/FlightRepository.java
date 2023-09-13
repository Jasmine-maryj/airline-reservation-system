package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f.availableSeats FROM Flight f WHERE f.id = :flightId")
    int findAvailableSeatsByFlight(long flight);
}
