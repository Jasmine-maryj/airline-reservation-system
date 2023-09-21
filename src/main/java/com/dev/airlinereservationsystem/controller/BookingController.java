package com.dev.airlinereservationsystem.controller;

import com.dev.airlinereservationsystem.dto.BookingDto;
import com.dev.airlinereservationsystem.dto.BookingDtoResponse;
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
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto) {
        String result = bookingService.createBooking(bookingDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookingDtoResponse>> getAllBookings() {
        List<BookingDtoResponse> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{bookingNumber}")
    public ResponseEntity<String> cancelBooking(@PathVariable String bookingNumber){
        bookingService.cancelBooking(bookingNumber);
        return ResponseEntity.ok("Cancelled the reservation");
    }
    @DeleteMapping("/delete/{bookingNumber}")
    public ResponseEntity<String> deleteBooking(@PathVariable String bookingNumber){
        bookingService.deleteBooking(bookingNumber);
        return ResponseEntity.ok("Deleted Successfully");
    }

}
