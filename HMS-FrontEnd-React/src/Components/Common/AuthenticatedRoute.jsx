import React from "react";
import { Outlet } from "react-router-dom";
import Login from "./Login";

export default function AuthenticatedRoute({path, ...props}) {

    function isUserLoggedIn() {
        var user = (sessionStorage.getItem("username"))
        if (user === null) {
            return false
        } else {
            return true
        }
    }

    if (isUserLoggedIn()) {
        //console.log("User logged in")
        return <Outlet/>
    } else {
        //console.log("User not logged in")
        return <Login/>
    }
    
}