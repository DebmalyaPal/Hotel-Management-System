import React, { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import AdminHeader from "./AdminHeader";


export default function EditTariff() {

    const location = useLocation();
    const [tariff] = useState(location.state.tariff)

    const [price, setPrice] = useState(tariff.price);

    const navigate = useNavigate();

    return (
        <>
            <AdminHeader/>
            <form className="mt-4 pt-3">
                <table className="table table-borderless table-light mx-auto"
                        style={{width:"35%"}}>
                    <thead>
                        <tr>
                            <th colSpan={2}>
                                <h3>TARIFF DETAILS</h3>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="text-end">Room Type</td>
                            <td className="text-center">
                                { tariff.category }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">AC / Non AC</td>
                            <td className="text-center">
                                { tariff.cooling }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">Max. Occuoancy</td>
                            <td className="text-center">
                                { tariff.occupancy }
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">Price</td>
                            <td className="text-center">
                                <input type="number" name="price" value={price}
                                    className="text-center"
                                    onChange={ (e) => setPrice(e.target.value) } />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info" style={{width:"50%"}} 
                                        onClick={ updateTariff }>
                                    UPDATE
                                </button>
                            </td>
                        </tr>
                    </tbody>

                </table>
            </form>
        </>
    )

    function updateTariff(event) {
        event.preventDefault();
        var updatedTariff = {
            id : tariff.id,
            category : tariff.category,
            cooling : tariff.cooling,
            occupancy : tariff.occupancy,
            price : Number(price)
        }
        
        axios.put(`http://localhost:8080/tariff/${tariff.id}`,
                updatedTariff,
                {
                    headers : {
                        'Content-type' : 'application/json'
                    }
                }).then( () => {
                    alert("TARIFF UPDATED SUCCESSFULLY !!!");
                }).catch( () => {
                    alert("TARIFF UPDATE UNSUCCESSFUL !!!");
                })
        navigate("/admin/tariff");
        
    }

}