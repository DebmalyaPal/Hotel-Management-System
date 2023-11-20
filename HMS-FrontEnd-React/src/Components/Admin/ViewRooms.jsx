import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import AdminHeader from "./AdminHeader";


export default function ViewRooms() {

    const location = useLocation();
    const [tariff] = useState(location.state.tariff);

    const [rooms, setRooms] = useState([]);

    useEffect( () => { displayRoomList(); }, []);

    return (
        <>
            <AdminHeader/>
            <table className="table table-borderless table-dark mx-auto mt-3"
                    style={{width:"75%"}}>
                <thead>
                    <tr>
                        <th colSpan={4}> <h3>{tariff.category.toUpperCase()} ROOMS </h3></th>
                    </tr>
                    <tr>
                        <th>Room No.</th>
                        <th>AC / Non AC</th>
                        <th>Occupancy</th>
                        <th>Price (in Rs.)</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        rooms.map(
                            room =>
                                <tr key={ room.roomId }>
                                    <td> {room.roomId} </td>
                                    <td> { room.tariff.cooling } </td>
                                    <td> { room.tariff.occupancy } </td>
                                    <td> { room.tariff.price }.00 INR </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
                
        </>
    )

    
    function displayRoomList() {
        axios.get(`http://localhost:8080/room/tariff/${tariff.id}`,
                    {
                        headers : {
                            'Content-type' : 'application/json'
                        }
                }).then( (response) => setRooms(response.data) )
                .catch( (response) => console.log("ERROR DISPLAYING LIST OF ROOMS BY TARIFF -> " + response.message))
    }

}