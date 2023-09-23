import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AirportService from '../services/AirportService.js';

export default function BookFlight() {
    const navigate = useNavigate();
    const [bookings, setBookings] = useState([]);

    const fetchBookings = async () => {
        try {
            const response = await AirportService.getBookings();
            setBookings(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchBookings();
    }, []);

    const updateBookingStatus = (bookingNumber, newStatus) => {
        setBookings((prevBookings) =>
            prevBookings.map((booking) =>
                booking.bookingNumber === bookingNumber ? { ...booking, status: newStatus } : booking
            )
        );
    };

    const handleCancelBooking = async (bookingNumber) => {
        try {
            await AirportService.cancelBooking(bookingNumber);
            updateBookingStatus(bookingNumber, 'CANCELLED');
            navigate('/bookings');
        } catch (error) {
            console.error(error);
        }
    };

    const handleDeleteBooking = async (bookingNumber) => {
        try {
            await AirportService.deleteBooking(bookingNumber);
            setBookings((prevBookings) =>
                prevBookings.filter((booking) => booking.bookingNumber !== bookingNumber)
            );
            navigate('/bookings');
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="container">
            <h1 className="text-center">List Airports</h1>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Airport Code</th>
                        <th>Flight Number</th>
                        <th>Origin</th>
                        <th>Destination</th>
                        <th>Arrival Time</th>
                        <th>Departure Time</th>
                        <th>Booking Date</th>
                        <th>Booking Number</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {bookings.map((booking) => (
                        <tr key={booking.bookingNumber}>
                            <td>{booking.airportCode}</td>
                            <td>{booking.flightNumber}</td>
                            <td>{booking.origin}</td>
                            <td>{booking.destination}</td>
                            <td>{booking.arrivalTime}</td>
                            <td>{booking.departureTime}</td>
                            <td>{booking.bookingDate}</td>
                            <td>{booking.bookingNumber}</td>
                            <td>{booking.status}</td>
                            <td>
                                <button
                                    className="btn btn-danger"
                                    onClick={() => handleCancelBooking(booking.bookingNumber)}
                                >
                                    Cancel
                                </button>
                                <button
                                    className="btn btn-danger"
                                    onClick={() => handleDeleteBooking(booking.bookingNumber)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
