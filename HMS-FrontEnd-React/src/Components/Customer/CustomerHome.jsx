import React, { Component } from "react";
import { Link } from "react-router-dom";
import CustomerHeader from "./CustomerHeader";


const style = {
    width:"25%",
    height:"100px",
    border:"2px solid red",
    borderRadius:"10px",
    backgroundColor:"lightblue",
    fontSize : "125%",
    fontWeight: "Bold",
    textDecoration: "None",
    color: "black",
}


class CustomerHome extends Component {

    render() {
        return (
            <div>
                <CustomerHeader/>
                <div className="d-flex flex-column mx-auto">
                    <div className="d-inline-flex justify-content-center">
                        <Link to={"/customer/tariff"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            TARIFF <br/> SECTION
                        </Link>
                        <Link to={"/customer/bookings"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            BOOKINGS <br/> SECTION
                        </Link>
                    </div>
                    <div className="d-inline-flex justify-content-center">
                        <Link to={"/customer/profile/view"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            PROFILE <br/> SECTION
                        </Link>
                    </div>
                </div>
            </div>
        )
    }
}

export default CustomerHome;