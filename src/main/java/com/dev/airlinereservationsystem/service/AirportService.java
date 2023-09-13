package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.AirportDto;
import com.dev.airlinereservationsystem.dto.FlightDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

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

    public void deleteAirport(Long id) {
        try{
            airportRepository.deleteById(id);
        }catch (Exception exception){
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }
}
