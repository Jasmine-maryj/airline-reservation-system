# Airline Reservation System

The Airline Reservation System is a web-based administrative application that allows administrators to manage flight and airport details. This system is designed to provide a user-friendly interface for administrators to oversee and make changes to flight and airport information.

# Table of Contents

1. Features
2. Getting Started
3. Prerequisites
4. Installation
5. Technologies Used
6. License

## Features
1. View Available Airports: Users can see a list of available airports in the system, along with their details such as name, location, and facilities.

2. Book a Flight: Users can search for flights based on various criteria like destination, departure date, and number of passengers. They can then book a flight and receive a booking confirmation.

3. View Available Flights: Users can search for available flights based on their travel preferences. The system displays a list of flights with relevant information such as departure time, arrival time, and ticket price.

4. Delete and Cancel Booking: Users can delete their bookings or cancel their reservations, freeing up seats for other passengers.

5. Edit Flight and Airport Details: Admin users can modify flight and airport information, such as changing departure times, adding new airports, or updating existing airport details.

# Getting Started

## Prerequisites
Before you can run the Airline Reservation System (Admin), ensure you have the following prerequisites:

1. Node.js installed on your system.
2. MySQL database server installed and running.
3. Java Development Kit (JDK) for running the Spring Boot backend

## Installation

1. Clone the repository to your local machine:
   `git clone https://github.com/your-username/airline-reservation-system.git`

2. Navigate to the project directory:
  `cd airline-reservation-system`
3. Install the project dependencies for the frontend (React):
   `cd frontend
    npm install`
4. Return to the project root directory:
  `cd ..`
5. Configure the MySQL database connection by editing the application.yaml file in the backend.
6. Build and run the Spring Boot backend
   `./mvnw clean install
    ./mvnw spring-boot:run`
7. Access the application in your web browser at `http://localhost:8080/api/v1/**`

## Technologies Used

- Spring Boot: Backend framework for building the server-side application.
- React: Frontend framework for building the user interface.
- Redux: State management library for React.
- HTML/CSS: For styling and structuring the front end.
- MySQL: Database for storing flight and airport information.
- RESTful API: For communication between the frontend and backend

## License
This project is licensed under the MIT License.

Thank you for reading!
