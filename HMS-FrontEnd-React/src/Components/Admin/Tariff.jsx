import React, { useEffect, useState } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";
import { useNavigate } from "react-router-dom";


export default function Tariff() {

    const navigate = useNavigate();

    useEffect( () => { displayTariff() }, [])

    const [tariffs, setTariffs] = useState([])

    return (
        <>
            <AdminHeader/>

            <div className="m-2 p-2">
                <h1 className="text-lg-justify" 
                    style={{color:"#ff4d4d", fontSize:"300%"}}>
                   TARIFFS
                </h1>
                {
                    tariffs.map(
                        tariff =>
                            <div className="d-inline-flex"
                                key={ tariff.id }>
                                <div className="text-justified m-2 p-2"
                                style={{border:"2px double pink",
                                        borderRadius:"15px",
                                        backgroundColor:"#8aafeb"}}>    
                                    Room Type   : { tariff.category }
                                    <br/>
                                    AC / Non AC : { tariff.cooling }
                                    <br/>
                                    Occupancy   : { tariff.occupancy }
                                    <br/>
                                    Price (in Rs.) : { tariff.price + ".00 INR" }
                                    <br/>
                                    <br/>
                                    <div className="p-1">
                                        <button className="btn btn-info m-1"
                                                onClick={ () => {addRoom(tariff)} }>
                                            ADD ROOM
                                        </button>
                                        <button className="btn btn-info m-1"
                                                onClick={ () => {viewRooms(tariff)} }>
                                            VIEW ROOMS
                                        </button>
                                        <button className="btn btn-info m-1"
                                                onClick={ () => {editPrice(tariff)} }>
                                            EDIT PRICE
                                        </button>
                                    </div>
                                </div>
                            </div>
                    )
                }
            </div>
        </>
    )


    function displayTariff() {
        axios.get('http://localhost:8080/tariff/')
            .then( response => setTariffs(response.data) )
            .catch( response => console.log("ERROR fetching Tariff -> " + response.message));
    }

    function addRoom(tariff) {
        var tariffId = tariff.id
        var roomData = {};
        axios.post(`http://localhost:8080/room/tariff/${tariffId}`,
                    roomData,
                    {
                        headers : {
                            'Content-type' : 'application/json'
                        }
                    }
                ).then( (response) => {
                    alert("Room Added Successfully !!!");
                }).catch( (response) => console.log("ERROR ADDING ROOM -> " + response.messgae));
    }

    function viewRooms(tariff) {
        navigate("/admin/tariff/viewRooms", { state : { tariff } })
    }

    function editPrice(tariff) {
        navigate("/admin/tariff/edit", { state : { tariff } })
    }



}




/*
import React, { Component } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";


class Tariff extends Component {

    constructor(props) {
        super(props)

        this.state = {
            tariffs : []
        }

        this.displayTariff = this.displayTariff.bind(this)
        this.addRoom = this.addRoom.bind(this)
    }
    
    componentDidMount() {
        this.displayTariff()
    }
    
    render() {
        return (
            <>
                <AdminHeader></AdminHeader>

                <div className="m-2 p-2">
                    
                    <h1 className="text-lg-justify" 
                        style={{color:"#ff4d4d", fontSize:"300%"}}>
                        TARIFFS
                    </h1>
                    
                    {
                        this.state.tariffs.map(
                            tariff =>
                                <div className="d-inline-flex"
                                    key={ tariff.id }>
                                    <div className="text-justified m-2 p-2"
                                    style={{border:"2px double pink",
                                            borderRadius:"15px",
                                            backgroundColor:"#8aafeb"}}>    
                                        Room Type   : { tariff.category }
                                        <br/>
                                        AC / Non AC : { tariff.cooling }
                                        <br/>
                                        Occupancy   : { tariff.occupancy }
                                        <br/>
                                        Price (in Rs.) : { tariff.price + ".00 INR" }
                                        <br/>
                                        <br/>
                                        <div className="p-1">
                                            <button className="btn btn-info m-1"
                                            onClick={this.addRoom}>
                                                ADD ROOM
                                            </button>
                                            <button className="btn btn-info m-1">VIEW ROOM</button>
                                            <button className="btn btn-info m-1">EDIT PRICE</button>
                                        </div>
                                    </div>
                            </div>
                    )
                }

            </div>
            </>
        )
    }


    displayTariff() {
        axios.get('http://localhost:8080/tariff/')
            .then( response => {this.setState({tariffs : response.data});
                                console.log(response.data);} )
            .catch( response => console.log("ERROR fetching Tariff -> " + response.message));
        
        return;
    }

    addRoom() {
        axios.post();
       alert("Room Added Successfully !!!");
       return;
    }

}

export default Tariff;
*/
