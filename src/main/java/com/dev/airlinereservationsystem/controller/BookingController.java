package com.dev.airlinereservationsystem.controller;

import com.dev.airlinereservationsystem.dto.BookingDto;
import com.dev.airlinereservationsystem.entity.Booking;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.service.BookingService;
import com.dev.airlinereservationsystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto, @PathVariable int seatNumber) {
        String result = bookingService.createBooking(bookingDto, seatNumber);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Booking booking = bookingService.updateBooking(id, updatedBooking);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully.");
    }


    @GetMapping("/available-seats/{flightId}")
    public ResponseEntity<List<Integer>> getAvailableSeats(@PathVariable Long flightId) {
        Flight flight = flightService.getFlightById(flightId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        List<Integer> availableSeats = bookingService.getAvailableSeats(flight);
        return ResponseEntity.ok(availableSeats);
    }

}
