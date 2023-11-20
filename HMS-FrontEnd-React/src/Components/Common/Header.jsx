import React, { Component } from "react";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'


const brandNameStyle = {
    padding : "2px",
    fontFamily : "Algerian, 'Monotype Corsiva'",
    fontSize : "300%",
}

class Header extends Component {

    render() {
        return (
            <>
                <header>
                    <div className="justify-content-center text-bg-secondary text-warning"
                        style={brandNameStyle}>
                        <b>THE &nbsp; MAJESTICA</b>
                    </div>
                </header>
            </>
        )
    }

}

export default Header