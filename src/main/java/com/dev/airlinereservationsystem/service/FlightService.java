package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.FlightDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.AirportRepository;
import com.dev.airlinereservationsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    public void addFlight(FlightDto flightDto, String code) {
        Optional<Airport> airportOptional = airportRepository.findByCode(code);
        if (airportOptional.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found");
        }

        // Create a new Flight entity
        Flight flight = new Flight();
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setAvailableSeats(flightDto.getAvailableSeats());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setFlightNumber(UUID.randomUUID().toString());
        flight.setDepartureAirport(airportOptional.get());

        // Save the flight
        flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void updateFlight(Long id, FlightDto updatedFlightDto) {
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));

        flight.setAvailableSeats(updatedFlightDto.getAvailableSeats());
        flight.setOrigin(updatedFlightDto.getOrigin());
        flight.setDestination(updatedFlightDto.getDestination());
        flight.setArrivalTime(updatedFlightDto.getArrivalTime());
        flight.setDepartureTime(updatedFlightDto.getDepartureTime());

        flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found"));
        if(flight != null){
            flightRepository.deleteById(id);
        }
    }

    public List<FlightDto> getFlightByAirport(Airport addedAirport) {
        List<Flight> flights = flightRepository.findByDepartureAirport(addedAirport);
        return flights.stream().map(this::mapToFlightDTO).toList();
    }

    private FlightDto mapToFlightDTO(Flight flight) {

        FlightDto flightDto = new FlightDto();
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDestination(flight.getDestination());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setBookings(flight.getBookings());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setAvailableSeats(flight.getAvailableSeats());
        return flightDto;
    }

    public void updateFlightWithFlightNumber(FlightDto updatedFlightDto, String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);

        flight.setAvailableSeats(updatedFlightDto.getAvailableSeats());
        flight.setOrigin(updatedFlightDto.getOrigin());
        flight.setDestination(updatedFlightDto.getDestination());
        flight.setArrivalTime(updatedFlightDto.getArrivalTime());
        flight.setDepartureTime(updatedFlightDto.getDepartureTime());

        flightRepository.save(flight);
    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public void removeFlightFromAirport(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);

        if (flight != null) {
            Airport airport = flight.getDepartureAirport();
            if (airport != null) {
                airport.getDepartingFlights().remove(flight);
                airportRepository.save(airport);
            }
            flightRepository.delete(flight);
        } else {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }
}
