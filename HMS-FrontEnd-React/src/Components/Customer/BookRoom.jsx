import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from 'axios';
import CustomerHeader from "./CustomerHeader";
import moment from "moment/moment";

export default function BookRoom() {

    const location = useLocation()
    const [tariff] = useState(location.state.tariff)

    const navigate = useNavigate();

    const [username] = useState(sessionStorage.getItem('username'))
    const transactionDate = useState(moment(new Date()).format("yyyy-MM-DD"))
    const [bookingDate, setBookingDate] = useState()
    const minValidBookingDate = () => {
        return moment(new Date()).format("yyyy-MM-DD")
    }
    const [roomId, setRoomId] = useState('')
    const [paymentMode, setPaymentMode] = useState('')
    const [amount, setAmount] = useState(0)
    
    return (
        <>
            <CustomerHeader/>
            <form className="mt-4 pt-4">
                <table className="table table-borderless table-light mx-auto"
                    style={{width:"30%"}}>
                    <thead>
                        <tr>
                            <th colSpan={2}>BOOK A ROOM</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="text-end">
                                Room Type : 
                            </td>
                            <td className="text-start">
                                { tariff.category }  { tariff.cooling }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Occupancy : 
                            </td>
                            <td className="text-start">
                                { tariff.occupancy }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Rate :
                            </td>
                            <td className="text-start">
                                { tariff.price }.00 INR
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Booking Date :
                            </td>
                            <td className="text-start">
                                <input type="date"
                                    min= {minValidBookingDate()}
                                    onChange={ (e) => {
                                        findRoom(e.target.valueAsDate);
                                    }}
                                    required/>
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Room No. : 
                            </td>
                            <td className="text-start">
                                { (roomId==='')?'<Enter Booking Date>':roomId }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Amount :
                            </td>
                            <td className="text-start">
                                { amount }.00 INR
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">
                                Payment Mode :
                            </td>
                            <td className="text-start">
                                <select name="paymentMode" defaultValue={""}
                                    onChange={ (e) => {setPaymentMode(e.target.value)}}
                                    required>
                                    <option value="" disabled>Payment Mode</option>
                                    <option value="Net Banking">Net Banking</option>
                                    <option value="Debit Card">Debit Card</option>
                                    <option value="Credit Card">Credit Card</option>
                                    <option value="UPI">UPI</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info mb-2"
                                    style={{width:"50%"}}
                                    onClick={ (e) => {bookRoom(e)} }>
                                    BOOK
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </>
    )
    

    function findRoom(date) {
        var tariffId = tariff.id;
        date = moment(date).format("yyyy-MM-DD")
        setBookingDate(date)
        var searchDate = (String)(date)
        axios.get(`http://localhost:9010/bookings/tariff/${tariffId}/date/${searchDate}/`,
                {
                    headers : {
                        'Content-type' : 'application/json'
                    }
            }).then( (response) => {
                setRoomId(response.data.roomId);
                setAmount(response.data.tariff.price);
            }).catch( (response) => {
                console.log("Error Finding Room on given Date -> " + response.message);
                alert("Room Not Found");
            })
    }


    function bookRoom(event) {
        event.preventDefault();
        var bookingInfo = {
            roomId,
            username,
            transactionDate : transactionDate[0],
            bookingDate,
            amount,
            paymentMode
        }
        
        if (roomId==='' || amount===0 || paymentMode==='') {
            alert('Please Fill In Details and Try Again!');
            return;
        }
        axios.post(`http://localhost:9010/bookings`,
                    bookingInfo,
                    {
                        headers : {
                            'Content-type' : 'application/json'
                        }
                    }
                ).then( (response) => {
                            alert("Room Booking Successful !!");
                            var bookingDetails = response.data;
                            navigate('/customer/bookings/view', { state : { booking : bookingDetails } })
                }).catch( (response) => {console.log("Error Booking Room -> " + response.message)})
    }

}