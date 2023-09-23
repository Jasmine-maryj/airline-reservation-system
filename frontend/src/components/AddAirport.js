import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from "react-router-dom";
import AirportService from '../services/AirportService.js';

export default function AddAirportForm() {

    const navigate = useNavigate();
    const locationParam = useLocation();
    const queryParam = new URLSearchParams(locationParam.search);
    const queryCode = queryParam.get("code");

    const [airport, setAirport] = useState({
        name: "",
        code: "",
        location: ""
    });

    const [isUpdate, setIsUpdate] = useState(false);

    useEffect(() => {

        if (queryCode) {
            setIsUpdate(true);
            setAirport(prevAirport => ({
                ...prevAirport,
                code: queryCode
            }));

            AirportService.getAirportByCode(queryCode)
                .then((res) => {
                    const airportData = res.data;
                    setAirport(prevAirport => ({
                        ...prevAirport,
                        name: airportData.name,
                        location: airportData.location
                    }));
                });
        }
    }, [locationParam]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setAirport(prevAirport => ({
            ...prevAirport,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const serviceFunction = isUpdate
                ? AirportService.updateAirport
                : AirportService.createAirport;

            const response = await serviceFunction(airport, queryCode);
            console.log(response.data);
            navigate("/airports");
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <br></br>
            <br></br>
            <br></br>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">

                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <label className="form-label">Name</label>
                                    <input
                                        type="text"
                                        placeholder="Enter Name"
                                        className="form-control"
                                        value={airport.name}
                                        name="name"
                                        onChange={handleChange}
                                    >
                                    </input>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">code</label>
                                    <input
                                        className="form-control"
                                        type="text"
                                        placeholder="Enter Code"
                                        name="code"
                                        value={airport.code}
                                        onChange={handleChange}
                                    >
                                    </input>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Location</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Location"
                                        name="location"
                                        value={airport.location}
                                        onChange={handleChange}
                                    >
                                    </input>
                                </div>
                                <button className="btn btn-success" onClick={(e) => handleSubmit(e)}>Submit</button>
                                <Link to="/airports" className="btn btn-danger">Cancel</Link>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};
