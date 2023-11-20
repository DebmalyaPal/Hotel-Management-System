import axios from "axios";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import CustomerHeader from "./CustomerHeader";

export default function EditProfile() {

    const navigate = useNavigate();

    const location = useLocation();
    const [profileInfo] = useState(location.state.profileInfo);

    const [name, setName] = useState(profileInfo.name);
    const [email] = useState(profileInfo.email)
    const [phone, setPhone] = useState(profileInfo.phone);
    const [oldPassword, setOldPassword] = useState("")
    const [newPassword, setNewPassword] = useState("")


    return(
        <>
            <CustomerHeader/>
            <form className="mt-4 pt-4">
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
                            <td className="text-start">
                                <input type="text" name="name" value={ name } 
                                    onChange={ (e) => setName(e.target.value) } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end"> Email (Username) : </td>
                            <td className="text-start"> { email } </td>
                        </tr>
                        <tr>
                            <td className="text-end">Phone No. : </td>
                            <td className="text-start">
                                <input type="tel" name="phone" value={ phone } 
                                    onChange={ (e) => setPhone(e.target.value) } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">Old Password : </td>
                            <td className="text-start">
                                <input type="password" name="oldPassword" value={ oldPassword } 
                                    onChange={ (e) => setOldPassword(e.target.value) } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">New Password : </td>
                            <td className="text-start">
                                <input type="password" name="newPassword" value={ newPassword } 
                                    onChange={ (e) => setNewPassword(e.target.value) } />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info"
                                        style={{width:"50%"}}
                                        onClick={ updateProfile }>
                                    UPDATE
                                </button>    
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </>
    )


    function updateProfile(event) {
        event.preventDefault();
        var customerInfo = {
            userId : 0,
            name : name.trim(),
            email : email.trim(),
            phone : phone.trim(),
            password : newPassword.trim()
        }
        axios.put(`http://localhost:9000/customer/edit/${email}`,
                customerInfo,
                {
                    headers : {
                        'Authorization' : 'Basic ' + window.btoa(
                            email + ':' + oldPassword
                        ) 
                    }
                }
            ).then( (response) => alert("Profile Updated Successfully !") )
            .catch( (response) => alert("Profile Update Failed !") )

        navigate("/customer/profile/view");
    }

}