package com.dev.airlinereservationsystem.dto;

import com.dev.airlinereservationsystem.entity.Booking;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Set<Booking> bookings = new HashSet<>();
}
