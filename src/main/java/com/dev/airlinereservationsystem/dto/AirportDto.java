package com.dev.airlinereservationsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirportDto {
    private String name;
    private String location;
    private String code;
    private List<FlightDto> departingFlights;
}
