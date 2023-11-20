import React, { useState } from "react";
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import Header from "./Header";

export default function Registration() {

    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [phone, setPhone] = useState("")
    const [password, setPassword] = useState("")

    const navigate = useNavigate();

    return(
        <>
            <Header></Header>
            <div className="mt-4 pt-4">
                <form>
                    <table className="table table-borderless bg-light mx-auto rounded-3"
                            style={{width:"25%"}}>
                        <tbody>
                            <tr><td></td></tr>
                            <tr>
                                <td className="text-start">NAME</td>
                                <td>
                                    <input type="text" name="name"
                                        placeholder="Enter Name" required
                                        onChange={ (e) => {setName(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr>
                                <td className="text-start">EMAIL ID</td>
                                <td>
                                    <input type="email" name="email"
                                        placeholder="Enter Email Id" required
                                        onChange={ (e) => {setEmail(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr>
                                <td className="text-start">PHONE No. </td>
                                <td>
                                    <input type="tel" name="phone" 
                                        placeholder="Enter Phone No." 
                                        pattern="[0-9]{10}" required
                                        onChange={ (e) => {setPhone(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr>
                                <td className="text-start">PASSWORD</td>
                                <td>
                                    <input type="password" name="password"
                                        placeholder="Enter Password" required
                                        onChange={ (e) => {setPassword(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr><td className="m-0 p-0"></td></tr>
                            <tr>
                                <td colSpan={2}>
                                    <button className="btn btn-info"
                                            style={{width:"50%"}}
                                            onClick={buttonClicked}>
                                        SIGN UP
                                    </button>
                                </td>
                            </tr>
                            <tr className="m-0 p-0">
                                <td colSpan={2}>
                                    Already have an Account?
                                    <Link to='/'className="text-decoration-none"> SIGN IN</Link>
                                </td>
                            </tr>
                            <tr><td></td></tr>
                        </tbody>
                    </table>
                </form>
            </div>
                
        </>
    )

    
    function buttonClicked(event) {
        event.preventDefault();
        var userDetails = { name : name,
                            email : email,
                            phone : phone,
                            password : password 
                        }
        axios.post("http://localhost:9000/customer/signup", 
                    userDetails,
                    {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
            ).then( response => {
                            alert("Signed Up Successfully");
                            return navigate("/");
                        } )
            .catch(error => console.log(error));
    }
}


/*
import React, { Component} from "react";
import axios from 'axios';
import { Link } from 'react-router-dom';
import Header from "./Header";

export default class Registration extends Component {

    constructor(props) {
        super(props)
        this.state = {
            name: '',
            email:'',
            phone:'',
            password: ''
        }
        this.handleEvent = this.handleEvent.bind(this)
        this.buttonClicked = this.buttonClicked.bind(this)
    }

    render() {
        return(
            <>
                <Header></Header>
                <div className="mt-4 pt-4">
                    <form>
                        <table className="table table-borderless bg-light mx-auto rounded-3"
                                style={{width:"25%"}}>
                            <tbody>
                                <tr><td></td></tr>
                                <tr>
                                    <td className="text-start">NAME</td>
                                    <td>
                                        <input type="text" name="name"
                                            placeholder="Enter Name" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-start">EMAIL ID</td>
                                    <td>
                                        <input type="email" name="email"
                                            placeholder="Enter Email Id" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-start">PHONE No. </td>
                                    <td>
                                        <input type="tel" name="phone" 
                                            placeholder="Enter Phone No." 
                                            pattern="[0-9]{10}" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="text-start">PASSWORD</td>
                                    <td>
                                        <input type="password" name="password"
                                            placeholder="Enter Password" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr><td className="m-0 p-0"></td></tr>
                                <tr>
                                    <td colSpan={2}>
                                        <button className="btn btn-info"
                                                style={{width:"50%"}}
                                                onClick={this.buttonClicked}>
                                            SIGN UP
                                        </button>
                                    </td>
                                </tr>
                                <tr className="m-0 p-0">
                                    <td colSpan={2}>
                                        Already have an Account?
                                        <Link to='/'className="text-decoration-none"> SIGN IN</Link>
                                    </td>
                                </tr>
                                <tr><td></td></tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                
            </>
        )
    }


    handleEvent(event) {
        this.setState({[event.target.name]:event.target.value})
    }

    buttonClicked(event) {
        event.preventDefault();
        axios.post("http://localhost:9000/customer/signup", this.state,
                    {
                        headers: {
                            'Content-Type': 'application/json',
                        }
                    }
            ).then( response => console.log(response) )
            .catch(error => console.log(error));
    }
}
*/