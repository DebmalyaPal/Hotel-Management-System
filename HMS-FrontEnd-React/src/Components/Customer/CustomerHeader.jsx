import React from "react";
import { Link, useNavigate } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';


const brandNameStyle = {
    margin : "0px 0px 5px 35px",
    padding : "0px",
    fontFamily : "'Monotype Corsiva', Algerian",
    fontSize : "175%"
}

const ulStyle = {
    margin : "0px 35px 5px 0px"
}
const liStyle = {
    padding:"0px 15px"
}


export default function CustomerHeader() {

    const navigate = useNavigate();

    return (
        <>
            <header>
                <nav className="navbar navbar-expand navbar-dark bg-dark">
                    <div>
                        <a href="/customer" className="navbar-brand text-wrap"
                            style={brandNameStyle}>
                            <b>THE &nbsp; MAJESTICA</b>
                        </a>
                    </div>
                    <ul className="navbar-nav navbar-collapse justify-content-end"
                        style={ulStyle}>
                        <li style={liStyle}>
                            <Link to="/customer/tariff" className="nav-link active">TARIFF</Link>
                        </li>
                        <li style={liStyle}>
                            <Link to="/customer/bookings" className="nav-link active">BOOKINGS</Link>
                        </li>
                        <li style={liStyle}>
                            <Link to="/customer/profile/view" className="nav-link active">MY PROFILE</Link>
                        </li>
                        <li style={liStyle}>
                            <button className="btn btn-dark"
                                    onClick={logout}>
                                LOGOUT
                            </button>
                        </li>
                    </ul>
                </nav>
            </header>
        </>
    )

    function logout() {
        sessionStorage.removeItem("username");
        alert("Logged Out Successfully !!");
        return navigate("/");
    }
}


/*
import React, { Component } from "react";
import { Link, Routes, Route } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';


const brandNameStyle = {
    margin : "0px 0px 5px 35px",
    padding : "0px",
    fontFamily : "'Monotype Corsiva', Algerian",
    fontSize : "175%"
}

const ulStyle = {
    margin : "0px 35px 5px 0px"
}
const liStyle = {
    padding:"0px 15px"
}

class CustomerHeader extends Component {

    render() {
        return (
            <>
                <header>
                    <nav className="navbar navbar-expand navbar-dark bg-dark">
                        <div>
                            <a href="http://www.google.com" className="navbar-brand text-wrap"
                                style={brandNameStyle}>
                                <b>THE &nbsp; MAJESTICA</b>
                            </a>
                        </div>
                        <ul className="navbar-nav navbar-collapse justify-content-end"
                            style={ulStyle}>
                            <li style={liStyle}>
                                <Link to="/customer/tariff" className="nav-link active">TARIFF</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/customer/bookings" className="nav-link active">BOOKINGS</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/customer/profile" className="nav-link active">MY PROFILE</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/logout" className="nav-link">LOGOUT</Link>
                            </li>
                        </ul>
                    </nav>
                </header>
            </>
        )

    }
}

export default CustomerHeader
*/