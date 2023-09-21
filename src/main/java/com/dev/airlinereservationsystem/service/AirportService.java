package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.AirportDto;
import com.dev.airlinereservationsystem.dto.FlightDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.AirportRepository;
import com.dev.airlinereservationsystem.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Airport addAirport(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setName(airportDto.getName());
        airport.setLocation(airportDto.getLocation());
        airport.setCode(airportDto.getCode());

        List<FlightDto> departingFlightsDto = airportDto.getDepartingFlights();
        if (departingFlightsDto != null) {
            List<Flight> departingFlights = new ArrayList<>();
            for (FlightDto flightDto : departingFlightsDto) {
                Flight flight = new Flight();
                flight.setOrigin(flightDto.getOrigin());
                flight.setDestination(flightDto.getDestination());
                flight.setAvailableSeats(flightDto.getAvailableSeats());
                flight.setArrivalTime(flightDto.getArrivalTime());
                flight.setDepartureTime(flightDto.getDepartureTime());
                flight.setFlightNumber(UUID.randomUUID().toString());
                flight.setDepartureAirport(airport); // Set the departure airport
                departingFlights.add(flight);
            }
            airport.setDepartingFlights(departingFlights);
        }

        return airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport updateAirport(Long id, AirportDto updatedAirport) {
        Airport airport = this.getAirportById(id);
        airport.setName(updatedAirport.getName());
        airport.setLocation(updatedAirport.getLocation());
        airport.setCode(updatedAirport.getCode());
        return airportRepository.save(airport);
    }

    public void deleteAllAirport() {
        airportRepository.deleteAll();
    }

    public Airport updateAirportByCode(String code, AirportDto updatedAirport) {
        Airport airport = airportRepository.findByCode(code).orElseThrow(()-> new ResourceNotFoundException("Airport not found"));
        airport.setName(updatedAirport.getName());
        airport.setLocation(updatedAirport.getLocation());
        airport.setCode(updatedAirport.getCode());
        return airportRepository.save(airport);
    }

    public Airport getAirportByCode(String code) {
        return airportRepository.findByCode(code).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
    }

    public void deleteAirportByAirportCode(String airportCode) {
        airportRepository.deleteByCode(airportCode);
    }

}
