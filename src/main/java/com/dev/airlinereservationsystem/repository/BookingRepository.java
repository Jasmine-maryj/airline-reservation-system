package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Booking;
import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.entity.User;
import com.dev.airlinereservationsystem.util.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserAndFlight(User user, Flight flight);

    Booking findByFlightAndSeatNumberAndBookingStatus(Flight flight, int seatNumber, BookingStatus reserved);

    Booking findByBookingNumber(String bookingNumber);

    void deleteByBookingNumber(String bookingNumber);
}
