import React, { useEffect, useState } from "react";
import axios from 'axios';
import AdminHeader from "./AdminHeader";
import { useNavigate } from "react-router-dom";

export default function Bookings() {
    
    useEffect( () => { displayBookingList(); }, [])

    const navigate = useNavigate();

    const [bookingList, setBookingList] = useState([])

    return (
        <>
            <AdminHeader/>
            <table className="table table-dark table-striped mx-auto"
                style={{width:"80%", marginTop:"10px"}}>
                <thead>
                    <tr>
                        <th colSpan={7}>
                            <h2>ALL BOOKINGS</h2>
                        </th>
                    </tr>
                    <tr>
                        <th>Booking Id</th>
                        <th>Customer Name</th>
                        <th>Room No.</th>
                        <th>Room Details</th>
                        <th>Booking Date</th>
                        <th>Amount (in Rs.)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        bookingList.map(
                            booking => 
                                <tr key={booking.bookingId}>
                                    <td>{ booking.bookingId }</td>
                                    <td>{ booking.customer.name }</td>
                                    <td>{ booking.room.roomId }</td>
                                    <td>{ booking.room.tariff.category } { booking.room.tariff.cooling }</td>
                                    <td>{ booking.bookingDate }</td>
                                    <td>{ booking.amount }.00 INR</td>
                                    <td>
                                        <button className="btn btn-info"
                                                onClick={ () => { viewBookingDetails(booking); } }>
                                            VIEW
                                        </button>
                                    </td>
                                </tr>
                            )
                    }
                </tbody>
            </table>
        </>
    )

    function displayBookingList() {
        console.log("View Bookings Details")
        
        axios.get('http://localhost:9010/bookings',
                {
                    headers : {
                        'Content-type' : 'application/json'
                    }
                }).then( response => setBookingList(response.data) )
                .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

    function viewBookingDetails(bookingData) {
        navigate("/admin/bookings/view", {state : { booking : bookingData }});
    }

}