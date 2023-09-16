package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Airport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByCode(String code);

    void deleteByCode(String airportCode);

}
