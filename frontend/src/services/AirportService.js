import axios from "axios";

const ARS_BASE_REST_API_URL = "http://localhost:8080/api/v1";



class AirportService{

    getAllAirports(){
        return axios.get(ARS_BASE_REST_API_URL+"/airports/all");
    }

    createAirport(airport){
        return axios.post(ARS_BASE_REST_API_URL+"/airports/add", airport);
    }

    createFlight(flight, airportCode) {
        const url = `${ARS_BASE_REST_API_URL}/flights/add?code=${airportCode}`;
        return axios.post(url, flight);
    }

    getAirportByCode(airportCode){
        return axios.get(ARS_BASE_REST_API_URL+"/airports/get/"+airportCode);
    }

    updateAirport(airport, airportCode){
        const url = `${ARS_BASE_REST_API_URL}/airports/update?code=${airportCode}`;
        return axios.put(url, airport);
    }

    deleteAirport(airportCode){
        return axios.delete(ARS_BASE_REST_API_URL+"/airports/"+airportCode);
    }

    updateFlight(flightData, flightNumber){
        const url = `${ARS_BASE_REST_API_URL}/flights/update?flightNumber=${flightNumber}`;
        return axios.put(url, flightData);
    }

    getFlightByFlightNumber(flightNumber){
        return axios.get(ARS_BASE_REST_API_URL+"/flights/get/"+flightNumber);
    }

    deleteFlight(flightNumber){
        return axios.delete(ARS_BASE_REST_API_URL+"/flights/delete/"+flightNumber);
    }

    bookFlight(airportFlightData){
        return axios.post(ARS_BASE_REST_API_URL+"/bookings/create", airportFlightData);
    }

    getBookings(){
        return axios.get(ARS_BASE_REST_API_URL+"/bookings/all-bookings");
    }

    cancelBooking(bookingNumber){
        return axios.put(ARS_BASE_REST_API_URL+"/bookings/"+bookingNumber);
    }
    deleteBooking(bookingNumber){
        return axios.delete(ARS_BASE_REST_API_URL+"/bookings/delete/"+bookingNumber);
    }
}
export default new AirportService()
