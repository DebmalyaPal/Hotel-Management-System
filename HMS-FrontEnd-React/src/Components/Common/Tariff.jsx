import React, { Component } from 'react'
import axios from 'axios';
import '../CSS/style.css'

class Tariff extends Component {

    constructor(props) {
        super(props)
        this.state = {
            tariff : []
        }
        this.viewTariff = this.viewTariff.bind(this)
        this.reset = this.reset.bind(this)
    }

    render() {
        return (
            <>

                <button onClick={this.viewTariff} className="main">VIEW TARIFF</button>
                <button onClick={this.reset} className="main">RESET</button>

                <table>
                    <thead>
                        <tr>
                            <th colSpan={5}><b>TARIFF</b></th>
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
                            this.state.tariff.map(
                                tariff =>
                                    <tr>
                                        <td>{tariff.type}</td>
                                        <td>{tariff.cooling}</td>
                                        <td>{tariff.occupancy}</td>
                                        <td>{tariff.price}</td>
                                        <td>
                                            <button>Book A Room</button>
                                        </td>
                                    </tr>
                                )
                        }
                    </tbody>
                </table>
            </>
        )
    }

    viewTariff() {
        axios.get('http://localhost:8080/tariff')
            .then( response => this.setState({ tariff : response.data }) )
            .catch( response => console.log("ERROR - " + response));
        return;
    }

    reset() {
        this.setState({tariff:[]});
        return;
    }


}

export default Tariff;
