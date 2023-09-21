package com.dev.airlinereservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "airport")
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "code", columnDefinition = "VARCHAR(255)")
    private String code;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Flight> departingFlights = new ArrayList<>();

    public Airport(String c, String n, String l){
        this.code = c;
        this.name = n;
        this.location = l;
    }
}
