import React, { useState } from "react";
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import Header from "./Header";


export default function Login(props) {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");

    const navigate = useNavigate();

    return(
        <>
            <Header></Header>
            <div className="mt-3 pt-5">
                <form>
                    <table className="table table-borderless bg-light mx-auto rounded-3"
                            style={{width:"25%"}}>
                        <tbody>
                            <tr><td></td></tr>
                            <tr>
                                <td className="text-start">EMAIL ID</td>
                                <td>
                                    <input type="email" name="username"
                                        placeholder="Enter User Email Id" required
                                        onChange={ (e) => {setUsername(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr><td></td></tr>
                            <tr>
                                <td className="text-start">PASSWORD </td>
                                <td>
                                    <input type="password" name="password"
                                        placeholder="Enter Password" required
                                        onChange={ (e) => {setPassword(e.target.value)}}/>
                                </td>
                            </tr>
                            <tr><td className="m-0 p-0"></td></tr>
                            <tr><td></td></tr>
                            <tr>
                                <td className="text-start">ROLE </td>
                                <td>
                                    <select name="role" defaultValue={""}
                                            onChange={ (e) => {setRole(e.target.value)}}
                                            style={{width:"90%"}}
                                            required>
                                        <option value="" disabled>--Choose Role--</option>
                                        <option value="customer">CUSTOMER</option>
                                        <option value="admin">ADMIN</option>
                                    </select>
                                </td>
                            </tr>
                            <tr><td className="m-0 p-0"></td></tr>
                            <tr>
                                <td colSpan={2}>
                                    <button className="btn btn-info"
                                            style={{width:"50%"}}
                                            onClick={buttonClicked}>
                                        SIGN IN
                                    </button>
                                </td>
                            </tr>
                            <tr className="m-0 p-0">
                                <td colSpan={2}>
                                    Don't have an Account?
                                    <Link to='/signup'className="text-decoration-none"> SIGN UP</Link>
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
        
        var loginRequestData = {
            email : username.trim(),
            password : password.trim()
        }
        
        if (role==="admin") {
            axios.post("http://localhost:9000/admin/signin", loginRequestData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Basic " + window.btoa(
                            loginRequestData.email + ":" + loginRequestData.password)
                    }
                }
            ).then( response => {
                if (response.status===200) {
                    alert("LOGIN SUCCESSFUL !!!");
                    sessionStorage.setItem("username", response.data.email);
                    return navigate("/admin");
                }
            })
            .catch( response => {
                            console.log("ERROR - " + response.status)
                            alert("Invalid Credentials!!")
                        }
                    )
        } else {
            axios.post("http://localhost:9000/customer/signin", loginRequestData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Basic " + window.btoa(
                            loginRequestData.email + ":" + loginRequestData.password)
                    }
                }
            ).then( response => {
                if (response.status===200) {
                    alert("LOGIN SUCCESSFUL !!!")
                    sessionStorage.setItem("username", response.data.email);
                    return navigate("/customer");
                }
            })
            .catch( response => {
                            console.log("ERROR - " + response)
                            alert("Invalid Credentials!!")
                        }
                    )
        }
        
    }

}


/*
import React, { Component } from "react";
import axios from 'axios';
import { Link, Navigate } from 'react-router-dom';
import Header from "./Header";


export default class Login extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username:'',
            password: '',
            role:''
        }
        this.handleEvent = this.handleEvent.bind(this)
        this.buttonClicked = this.buttonClicked.bind(this)
        
    }

    render() {
        return(
            <>
                <Header></Header>
                <div className="mt-3 pt-5">
                    <form>
                        <table className="table table-borderless bg-light mx-auto rounded-3"
                                style={{width:"25%"}}>
                            <tbody>
                                <tr><td></td></tr>
                                <tr>
                                    <td className="text-start">EMAIL ID</td>
                                    <td>
                                        <input type="email" name="username"
                                            placeholder="Enter User Email Id" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr><td></td></tr>
                                <tr>
                                    <td className="text-start">PASSWORD </td>
                                    <td>
                                        <input type="password" name="password"
                                            placeholder="Enter Password" required
                                            onChange={this.handleEvent}/>
                                    </td>
                                </tr>
                                <tr><td className="m-0 p-0"></td></tr>
                                <tr><td></td></tr>
                                <tr>
                                    <td className="text-start">ROLE </td>
                                    <td>
                                        <select name="role"
                                                defaultValue={""}
                                                onChange={this.handleEvent}
                                                style={{width:"90%"}}
                                                required>
                                            <option value="" disabled>--Choose Role--</option>
                                            <option value="customer">CUSTOMER</option>
                                            <option value="admin">ADMIN</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr><td className="m-0 p-0"></td></tr>
                                <tr>
                                    <td colSpan={2}>
                                        <button className="btn btn-info"
                                                style={{width:"50%"}}
                                                onClick={this.buttonClicked}>
                                            SIGN IN
                                        </button>
                                    </td>
                                </tr>
                                <tr className="m-0 p-0">
                                    <td colSpan={2}>
                                        Don't have an Account?
                                        <Link to='/signup'className="text-decoration-none"> SIGN UP</Link>
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
        
        var loginRequestData = {
            email : this.state.username.trim(),
            password : this.state.password.trim()
        }

        console.log(this.state)
        if (this.state.role==="admin") {
            axios.post("http://localhost:9000/admin/signin", loginRequestData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Basic " + window.btoa(
                            loginRequestData.email + ":" + loginRequestData.password)
                    }
                }
            ).then( response => {
                console.log(response); 
                if (response.status===200) {
                    alert("LOGIN SUCCESSFUL !!!");
                    sessionStorage.setItem("username", response.data.username);

                    return <Navigate to={"/customer"}/>;
                }
            })
            .catch( response => {
                            console.log("ERROR - " + response)
                            alert("Invalid Credentials!!")
                        }
                    )
        } else {
            axios.post("http://localhost:9000/customer/signin", loginRequestData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Basic " + window.btoa(
                            loginRequestData.email + ":" + loginRequestData.password)
                    }
                }
            ).then( response => {
                console.log(response); 
                if (response.status===200) {
                    alert("LOGIN SUCCESSFUL !!!")
                    sessionStorage.setItem("username", response.data.email);
                    //this.context.router.history.push('/customer');
                    //this.props.history.push('/customer');  //Navigate to "CUSTOMER Home Page"
                    return <Navigate to={"/customer"}/>;
                }
            })
            .catch( response => {
                            console.log("ERROR - " + response)
                            alert("Invalid Credentials!!")
                        }
                    )
        }
    }

}*/