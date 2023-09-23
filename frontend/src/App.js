import './App.css';
import React from 'react';
import Header from './components/Header.js';
import Footer from './components/Footer.js';
import Hero from './components/Hero.js';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Airport from './components/Airport';
import AddFlight from './components/AddFlight.js';
import AddAirportForm from './components/AddAirport';
import BookFlight from './components/Book';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <div className="container">
          <Routes>
            <Route path="/" exact element={<Hero />} />
            <Route path="/airports" element={<Airport />} />
            <Route path="/add-airport" element={<AddAirportForm />} />
            <Route path="/add-flight" element={<AddFlight />} />
            <Route path="/edit-airport" element={<AddAirportForm />} />
            <Route path="/edit-flight" element={<AddFlight />} />
            <Route path="/bookings" element={<BookFlight />} />
          </Routes>
        </div>
        <Footer />
      </Router>
    </div>
  );
}

export default App;