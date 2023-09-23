import React, { useState, useEffect } from "react";
import AirportService from "../services/AirportService";
import { Link, useNavigate } from "react-router-dom";

export default function ListAirport() {
  const [airports, setAirports] = useState([]);

  const navigate = useNavigate()

  const getAllAirports = () => {
    AirportService.getAllAirports()
      .then((res) => {
        setAirports(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    getAllAirports();
  }, []);

  const deleteAirport = (airportCode) => {
    AirportService.deleteAirport(airportCode)
      .then((res) => {
        console.log(airportCode);
        getAllAirports();
        navigate("/airports")
      })
      .catch((error) => console.log(error));
  };

  const removeFlightFromAirport = (flightNumber) => {
    AirportService.deleteFlight(flightNumber)
      .then((res) => {
        console.log(flightNumber);
        getAllAirports();
        navigate("/airports")
      })
      .catch((error) => console.log(error));
  };

  const bookFlight = (airportCode, flightNumber) =>{
    const airportFlightData = {airportCode, flightNumber};
    AirportService.bookFlight(airportFlightData).then((res) => {
      console.log(flightNumber, res.data);
      navigate("/bookings")
    })
    .catch((error) => console.log(error));
  }



  return (
    <div className="container">
      <h1 className="text-center">List Airports</h1>
      <Link to="/add-airport" className="btn btn-primary mb-2">
        Add Airport
      </Link>
      <table className="table table-bordered table-striped">
        <thead>
          <tr>
            <th>Airport Name</th>
            <th>Airport Code</th>
            <th>Location</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {airports.map((airport) => (
            <tr key={airport.code}>
              <td>{airport.name}</td>
              <td>{airport.code}</td>
              <td>{airport.location}</td>
              <td>
                <Link to={`/add-flight?code=${airport.code}`} style={{ marginLeft: "0px" }} className="btn btn-success">Add Flight</Link>
                <Link to={`/edit-airport?code=${airport.code}`} style={{ marginLeft: "30px" }} className="btn btn-info">Edit</Link>
                <button className="btn btn-danger" style={{ marginLeft: "30px" }} onClick={() => deleteAirport(airport.code)}>
                  Delete Airport
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {airports.map((airport) => (
        <div key={airport.code}>
          <h2>{airport.name} - Flights:</h2>
          <table className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>Flight Number</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Arrival Time</th>
                <th>Departure Time</th>
                <th>AvailableSeats</th>
                <th style={{ width: "350px" }}>Actions</th>
              </tr>
            </thead>
            <tbody>
              {airport.departingFlights &&
                airport.departingFlights.map((flight) => (
                  <tr key={flight.id}>
                    <td>{flight.flightNumber}</td>
                    <td>{flight.origin}</td>
                    <td>{flight.destination}</td>
                    <td>{flight.arrivalTime}</td>
                    <td>{flight.departureTime}</td>
                    <td>{flight.availableSeats}</td>
                    <td>
                      <Link to={`/edit-flight?flightNumber=${flight.flightNumber}`} style={{ marginRight: "30px" }} className="btn btn-info">Edit</Link>
                      <button
                        className="btn btn-danger" style={{ marginRight: "30px" }} onClick={() => removeFlightFromAirport(flight.flightNumber)}>Remove Flight</button>

                      <button className="btn btn-success" onClick={() => bookFlight(airport.code, flight.flightNumber)}>Book</button>

                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
          <hr />
        </div>
      ))}
    </div>
  );
}