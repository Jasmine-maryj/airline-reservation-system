package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.BookingDto;
import com.dev.airlinereservationsystem.dto.BookingDtoResponse;
import com.dev.airlinereservationsystem.entity.Airport;
import com.dev.airlinereservationsystem.entity.Booking;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.entity.User;
import com.dev.airlinereservationsystem.handler.AlreadyBookedException;
import com.dev.airlinereservationsystem.handler.InvalidCancellationException;
import com.dev.airlinereservationsystem.handler.ResourceNotFoundException;
import com.dev.airlinereservationsystem.repository.BookingRepository;
import com.dev.airlinereservationsystem.repository.FlightRepository;
import com.dev.airlinereservationsystem.repository.UserRepository;
import com.dev.airlinereservationsystem.util.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    public String createBooking(BookingDto bookingDto) {


        Booking booking = new Booking();
        booking.setBookingNumber(UUID.randomUUID().toString());

        Flight flight = flightRepository.findByFlightNumber(bookingDto.getFlightNumber());
        Airport airport = flight.getDepartureAirport();
        booking.setFlight(flight);
        booking.setBookingDate(new Date());
        booking.setDepartureAirport(airport);
        booking.setBookingStatus(BookingStatus.RESERVED);

        bookingRepository.save(booking);

        return "Booked Successfully";
    }

    public List<BookingDtoResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDtoResponse> bookingDtoResponses = bookings.stream().map(this::mapToBookingDtoResponse).toList();
        return bookingDtoResponses;
    }

    private BookingDtoResponse mapToBookingDtoResponse(Booking booking) {
        BookingDtoResponse bookingDtoResponse = new BookingDtoResponse();
        bookingDtoResponse.setAirportCode(booking.getDepartureAirport().getCode());
        bookingDtoResponse.setFlightNumber(booking.getFlight().getFlightNumber());
        bookingDtoResponse.setOrigin(booking.getFlight().getOrigin());
        bookingDtoResponse.setDestination(booking.getFlight().getDestination());
        bookingDtoResponse.setArrivalTime(booking.getFlight().getArrivalTime());
        bookingDtoResponse.setDepartureTime(booking.getFlight().getDepartureTime());
        bookingDtoResponse.setStatus(String.valueOf(BookingStatus.RESERVED));
        bookingDtoResponse.setBookingDate(booking.getBookingDate());
        bookingDtoResponse.setBookingNumber(booking.getBookingNumber());
        return bookingDtoResponse;
    }


//    public List<Integer> getAvailableSeats(Flight flight) {
//        List<Integer> availableSeats = new ArrayList<>();
//        int totalSeats = flightRepository.findAvailableSeatsByFlight(flight.getId());
//        for (int seatNumber = 1; seatNumber <= totalSeats; seatNumber++) {
//            boolean isSeatAvailable = isSeatAvailable(flight, seatNumber);
//            if (isSeatAvailable) {
//                availableSeats.add(seatNumber);
//            }
//        }
//        return availableSeats;
//    }
//
//    private boolean isSeatAvailable(Flight flight, int seatNumber) {
//        Booking booking = bookingRepository.findByFlightAndSeatNumberAndBookingStatus(flight, seatNumber, BookingStatus.RESERVED);
//        return booking == null;
//    }
//
////    private boolean hasUserBookedFlight(User user, Flight flight){
////        List<Booking> bookings = bookingRepository.findByUserAndFlight(user, flight);
////        return bookings.stream().anyMatch(booking -> booking.getFlight().equals(flight));
////    }
//
//    public Booking getBookingById(Long id) {
//        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking Not found"));
//        return booking;
//    }
//
//    public List<Booking> getAllBookings() {
//        return bookingRepository.findAll();
//    }
//
//    public Booking updateBooking(String bookingNumber, Booking updatedBooking) {
//        Booking booking = bookingRepository.findByBookingNumber(bookingNumber);
//        if(updatedBooking.getBookingStatus().equals(BookingStatus.CANCELED)){
//            booking = cancelReservation(booking);
//        }
//        return booking;
//    }
//
//    private Booking cancelReservation(Booking booking) {
//        if (booking.getBookingStatus() == BookingStatus.RESERVED) {
//            booking.setBookingStatus(BookingStatus.CANCELED);
//            return bookingRepository.save(booking);
//        } else {
//            throw new InvalidCancellationException("This booking cannot be canceled.");
//        }
//    }
//
//    public void deleteBooking(String bookingNumber) {
//        try{
//            bookingRepository.deleteByBookingNumber(bookingNumber);
//        }catch (Exception ex){
//            throw new ResourceNotFoundException("Booking Not Found");
//        }
//    }
}
