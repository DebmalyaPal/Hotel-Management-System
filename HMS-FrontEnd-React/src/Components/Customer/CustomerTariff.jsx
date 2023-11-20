import React, { useEffect, useState } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import CustomerHeader from "./CustomerHeader";
import { useNavigate } from "react-router-dom";



export default function CustomerTariff() {

    const [tariffs, setTariffs] = useState([])

    useEffect( () => { displayTariff(); }, [])

    const navigate = useNavigate();

    return (
        <>          
            <CustomerHeader/>      
            <table className="table table-dark table-striped mx-auto"
                style={{width:"80%", marginTop:"15px"}}>
                <thead>
                    <tr>
                        <th colSpan={5}>
                            <h4>TARIFF</h4>
                        </th>
                    </tr>
                    <tr>
                        <th>Room Type</th>
                        <th>AC / Non AC</th>
                        <th>Occupancy</th>
                        <th>Price (in Rs.)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        tariffs.map(
                            tariff => 
                                <tr key={tariff.id}>
                                    <td>{tariff.category}</td>
                                    <td>{tariff.cooling}</td>
                                    <td>{tariff.occupancy}</td>
                                    <td>{tariff.price}</td>
                                    <td>
                                        <button className="btn btn-primary btn-sm"
                                                onClick={ () => { bookRoom(tariff) } }>
                                            Book A Room
                                        </button>
                                    </td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
        </>
    )

    function displayTariff() {
        axios.get('http://localhost:8080/tariff/')
            .then( response => setTariffs(response.data) )
            .catch( response => console.log("ERROR fetching Tariff" + response));
    }

    function bookRoom(tariff) {
        navigate('/customer/tariff/book', { state : { tariff } })
    }

}




/*
import React, { Component } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import CustomerHeader from "./CustomerHeader";



class CustomerTariff extends Component {

    constructor(props) {
        super(props)

        this.state = {
            tariffs : []
        }

        this.displayTariff = this.displayTariff.bind(this)
    }

    componentDidMount() {
        this.displayTariff()
    }

    render() {
        return (
            <>          
                <CustomerHeader/>      
                <table className="table table-dark table-striped mx-auto"
                    style={{width:"80%", marginTop:"15px"}}>
                    <thead>
                        <tr>
                            <th colSpan={5}>
                                <h4>TARIFF</h4>
                            </th>
                        </tr>
                        <tr>
                            <th>Room Type</th>
                            <th>AC / Non AC</th>
                            <th>Occupancy</th>
                            <th>Price (in Rs.)</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.tariffs.map(
                                tariff => 
                                    <tr>
                                        <td>{tariff.category}</td>
                                        <td>{tariff.cooling}</td>
                                        <td>{tariff.occupancy}</td>
                                        <td>{tariff.price}</td>
                                        <td>
                                            <button class="btn btn-primary btn-sm">Book A Room</button>
                                        </td>
                                    </tr>
                                )
                            }
                    </tbody>
                </table>
            </>
        )
    }



    displayTariff() {
        axios.get('http://localhost:8080/tariff/')
            .then( response => this.setState({tariffs : response.data}) )
            .catch( response => console.log("ERROR fetching Tariff" + response));
        
        return;
    }

}

export default CustomerTariff;
*/
