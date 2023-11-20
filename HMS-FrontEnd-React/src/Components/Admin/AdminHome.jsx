import React, { Component } from "react";
import { Link } from "react-router-dom";
import AdminHeader from "./AdminHeader";


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

class AdminHome extends Component {
    
    render() {

        return (

            <>
                <AdminHeader/>
                <div className="d-flex flex-column mx-auto">
                    <div className="d-inline-flex justify-content-center">
                        <Link to={"/admin/tariff"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            TARIFF <br/> SECTION
                        </Link>
                        <Link to={"/admin/bookings"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            BOOKINGS <br/> SECTION
                        </Link>
                    </div>
                    <div className="d-inline-flex  justify-content-center">
                        <Link to={"/admin/department"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            DEPARTMENT <br/> SECTION
                        </Link>
                        <Link to={"/admin/employees"} 
                            className="m-5 py-3 text-justify text-wrap"
                            style={style}>
                            EMPLOYEES <br/> SECTION
                        </Link>
                    </div>
                </div>
            </>
        )

    }
}

export default AdminHome