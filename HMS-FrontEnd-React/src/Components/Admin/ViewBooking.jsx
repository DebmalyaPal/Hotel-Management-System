import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import AdminHeader from "./AdminHeader";

export default function ViewBooking() {
    
    const location = useLocation();
    const [booking]  = useState(location.state.booking);
    
    return (
        <>
            <AdminHeader/>
            <table className="table table-dark table-borderless border border-primary mx-auto mt-4 pt-4"
                    style={{width:"60%"}}>
                <thead>
                    <tr className="border-bottom border-primary">
                        <th colSpan={4}>
                            <h2>BOOKING DETAILS</h2>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colSpan={4}><h5><u>ROOM DETAILS</u></h5></td>
                    </tr>
                    <tr className="border-bottom">
                        <td>
                            Room No. : { booking.room.roomId }
                        </td>
                        <td>
                            Room Type : <>{ booking.room.tariff.category} {booking.room.tariff.cooling }</>
                        </td>
                        <td>
                            Occupancy : { booking.room.tariff.occupancy } Person
                        </td>
                        <td>
                            Rate : { booking.room.tariff.price }.00 INR
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={4}><h5><u>CUSTOMER DETAILS</u></h5></td>
                    </tr>
                    <tr className="border-bottom">
                        <td>
                            Name : { booking.customer.name }
                        </td>
                        <td colSpan={2}>
                            Email : { booking.customer.email }
                        </td>
                        <td>
                            Phone : { booking.customer.phone }
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={4}><h5><u>BOOKING & PAYMENT DETAILS</u></h5></td>
                    </tr>
                    <tr>
                        <td colSpan={4}>
                            Booking Id : { booking.bookingId }
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            Transaction Date : { booking.transactionDate }
                        </td>
                        <td colSpan={2}>
                            Booking Date : { booking.bookingDate }
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            Amount : { booking.amount }.00 INR
                        </td>
                        <td colSpan={2}>
                            Payment Mode : { booking.paymentMode }
                        </td>
                    </tr>
                </tbody>
            </table>
        </>
    )

}