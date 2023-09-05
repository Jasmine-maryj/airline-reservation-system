package com.dev.airlinereservationsystem.controller;

import com.dev.airlinereservationsystem.dto.AirportDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;


    @PostMapping("/add")
    public ResponseEntity<Airport> addAirport(@RequestBody AirportDto airportDto) {
        Airport addedAirport = airportService.addAirport(airportDto);
        return ResponseEntity.ok(addedAirport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        if (airport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airport);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody AirportDto updatedAirport) {
        Airport airport = airportService.updateAirport(id, updatedAirport);
        if (airport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully.");
    }
}
