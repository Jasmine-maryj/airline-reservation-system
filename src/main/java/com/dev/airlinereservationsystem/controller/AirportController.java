package com.dev.airlinereservationsystem.controller;

import com.dev.airlinereservationsystem.dto.AirportDto;
import com.dev.airlinereservationsystem.dto.AirportWithFlightsDto;
import com.dev.airlinereservationsystem.dto.FlightDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.service.AirportService;
import com.dev.airlinereservationsystem.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@Slf4j
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;


    @PostMapping("/add")
    public ResponseEntity<AirportWithFlightsDto> addAirport(@RequestBody AirportDto airportDto) {
        Airport addedAirport = airportService.addAirport(airportDto);

        List<FlightDto> flights = flightService.getFlightByAirport(addedAirport);

        log.info("{}", flights);
        AirportWithFlightsDto airport = new AirportWithFlightsDto();
        airport.setName(addedAirport.getName());
        airport.setCode(addedAirport.getCode());
        airport.setLocation(addedAirport.getLocation());
        airport.setDepartingFlights(flights);


        return new ResponseEntity<>(airport, HttpStatus.CREATED);
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
    public ResponseEntity<List<AirportWithFlightsDto>> getAllAirportsWithFlights() {
        List<Airport> airports = airportService.getAllAirports();
        List<AirportWithFlightsDto> airportDtos = new ArrayList<>();

        for (Airport airport : airports) {
            List<FlightDto> flights = flightService.getFlightByAirport(airport);

            AirportWithFlightsDto airportDto = new AirportWithFlightsDto();
            airportDto.setName(airport.getName());
            airportDto.setCode(airport.getCode());
            airportDto.setLocation(airport.getLocation());
            airportDto.setDepartingFlights(flights);

            airportDtos.add(airportDto);
        }

        return ResponseEntity.ok(airportDtos);
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

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllAirport() {
        airportService.deleteAllAirport();
        return ResponseEntity.ok("Airports deleted successfully.");
    }
}
