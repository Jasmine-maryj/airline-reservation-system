package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.FlightDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public void addFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(UUID.randomUUID().toString());
        flight.setAvailableSeats(flightDto.getAvailableSeats());
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setDepartureTime(flightDto.getDepartureTime());

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
        return flightDto;
    }
}
