import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CustomerHeader from "./CustomerHeader";


export default function ViewProfile() {

    const navigate = useNavigate();
    
    useEffect( () => { getMyInfo() }, [] );

    const [myInfo, setMyInfo] = useState({});

    const getMyInfo = () => {
        var username = sessionStorage.getItem("username")
        axios.get(`http://localhost:9000/customer/username/${username}`,
                {
                    headers : {
                        'Content-type' : 'application/json'
                    }
                }).then( (response) => setMyInfo(response.data) )
                .catch( (response) => console.log('Error -> ' + response.message) )
    }

    return(
        <>
            <CustomerHeader/>
            <div className="mt-5 pt-5">
                <table className="table table-borderless bg-light mx-auto rounded-3"
                        style={{width:"30%"}}>
                <thead>
                        <tr>
                            <th colSpan={2}>
                                <h3>MY PROFILE</h3>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="text-end">Name : </td>
                            <td className="text-start"> { myInfo.name }</td>
                        </tr>
                        <tr>
                            <td className="text-end"> Email (Username) : </td>
                            <td className="text-start"> { myInfo.email } </td>
                        </tr>
                        <tr>
                            <td className="text-end">Phone No. : </td>
                            <td className="text-start"> { myInfo.phone } </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info"
                                        style={{width:"50%"}}
                                        onClick={ updateProfile }>
                                    EDIT
                                </button>    
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
        </>
    )

    
    function updateProfile() {
        var profileInfo = {
            email : myInfo.email.trim(),
            name : myInfo.name.trim(),
            phone : myInfo.phone.trim()
        }
        navigate("/customer/profile/edit", {state : { profileInfo }})
    }

}