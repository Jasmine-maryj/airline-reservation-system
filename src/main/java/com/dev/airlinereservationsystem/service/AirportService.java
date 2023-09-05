package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.AirportDto;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        airport.setDepartingFlights(airportDto.getDepartingFlights());
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
        airport.setDepartingFlights(updatedAirport.getDepartingFlights());
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
