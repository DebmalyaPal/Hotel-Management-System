import React, { Component } from "react";
import Header from "./Header";

export default class ErrorPage extends Component {
    render() {
        return(
            <>
                <Header></Header>
                <div>
                    <h1>ERROR 404 - PAGE UNDEFINED</h1>
                    <h4>INVALID REQUEST</h4>
                </div>
            </>

        )
    }
}