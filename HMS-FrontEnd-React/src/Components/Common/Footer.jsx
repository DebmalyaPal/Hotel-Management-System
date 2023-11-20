import React, { Component } from 'react'


const footerStyle = {
    position: "absolute",
    margin: "0",
    left: "0",
    bottom: "0",
    width: "100%"
}

class Footer extends Component {
    render() {
        return (
            <>
                <footer className='text-bg-secondary' style={footerStyle}>
                    <div className='row row-cols-4 mx-3'>
                        <div className='col'>
                            <p className='p-0 m-0'><b>The Majestica</b></p>
                            <p className='p-0 m-0'>India's Most Loved Hotel Chain</p>
                            <p className='p-0 m-0'>Exquisite Living & Dining Experience
                                <br/>since 1999
                            </p>
                        </div>
                        <div className='col'>
                            <p className='p-0 m-0'> <b>REACH US</b> </p>
                            <p className='p-0 m-0'>
                                Plot No. - CBD/2, Action Area - II,
                                <br></br>
                                New Town, Rajarhat, Kolkata - 700156, 
                                <br></br>
                                West Bengal, India                                
                            </p>
                        </div>
                        <div className='col'>
                            <p className='p-0 m-0'> <b>CONTACT US</b> </p>
                            <p className='p-0 m-0'>&#9993; info@majestica.com</p>
                            <p className='p-0 m-0'>&#9742; 033 2655 2491</p>
                            <p className='p-0 m-0'>&#9990; +91 9062829894</p>
                            
                        </div>
                        <div className='col'>
                            <p className='p-0 m-0'>
                                <b>DEVELOPER</b>
                                <br></br>
                                DEBMALYA PAL
                            </p>
                        </div>
                    </div>
                    <hr className="m-0 p-0"></hr>
                    <div className="m-0 bg-dark">
                        Copyright &copy; The Majestica (India) Private Limited - All Rights Reserved
                    </div>
                </footer>
            </>
        )
    }
}

export default Footer;
