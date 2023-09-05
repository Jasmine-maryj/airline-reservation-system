package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
