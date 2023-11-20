import React from "react";
import { Link, useNavigate } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'


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


export default function AdminHeader() {

    const navigate = useNavigate();

    return (
        <>
            <header>
                <nav className="navbar navbar-expand navbar-dark bg-dark">
                    <div>
                        <a href="/admin" className="navbar-brand text-wrap"
                            style={brandNameStyle}>
                            <b>THE &nbsp; MAJESTICA</b>
                        </a>
                    </div>
                    <ul className="navbar-nav navbar-collapse justify-content-end"
                        style={ulStyle}>
                        <li style={liStyle}>
                            <Link to="/admin/tariff" className="nav-link active">TARIFF</Link>
                        </li>
                        <li style={liStyle}>
                            <Link to="/admin/department" className="nav-link active">DEPARTMENT</Link>
                        </li>
                        <li style={liStyle}>
                            <Link to="/admin/employees" className="nav-link active">EMPLOYEES</Link>
                        </li>
                        <li style={liStyle}>
                            <Link to="/admin/bookings" className="nav-link active">BOOKINGS</Link>
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
import { Link, Outlet, Route, Router, Routes } from "react-router-dom";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'


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

class AdminHeader extends Component {

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
                                <Link to="/admin/tariff" className="nav-link">TARIFF</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/admin/department" className="nav-link">DEPARTMENT</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/admin/employees" className="nav-link">EMPLOYEES</Link>
                            </li>
                            <li style={liStyle}>
                                <Link to="/admin/bookings" className="nav-link">BOOKINGS</Link>
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

export default AdminHeader
*/