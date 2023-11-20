import axios from "axios";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import AdminHeader from "./AdminHeader";

export default function AddEmployee(props) {

    const navigate = useNavigate();

    const location = useLocation();
    const [department] = useState(location.state.department);
    
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [salary, setSalary] = useState(0);

    return(
        <>
            <AdminHeader/>
            <form className="mt-3 pt-4">
                <table className="table table-borderless bg-light mx-auto rounded-3"
                        style={{width:"25%"}}>
                    <thead>
                        <tr>
                            <th colSpan={2}>
                                <h3>EMPLOYEE DETAILS</h3>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="text-end">Name : </td>
                            <td>
                                <input type="text" name="name" required
                                        onChange={ (e) => {setName(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end"> Department : </td>
                            <td className="text-center"> { department.departmentName } </td>
                        </tr>
                        <tr>
                            <td className="text-end">Email Id : </td>
                            <td>
                                <input type="email" name="email" required
                                        onChange={ (e) => {setEmail(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end"> Phone No. :</td>
                            <td>
                                <input type="tel" name="phone" required
                                        onChange={ (e) => {setPhone(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">Salary : </td>
                            <td>
                                <input type="number" name="salary" required
                                        onChange={ (e) => {setSalary(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info"
                                        style={{width:"50%"}}
                                        onClick={ addEmployee }>
                                    SUBMIT
                                </button>    
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </>
    )

    function addEmployee(event) {
        event.preventDefault();
        var employeeDetails = {
            name : name.trim(),
            email : email.trim(),
            phone : phone.trim(),
            salary : Number(salary.trim())
        };
        var departmentId = department.departmentId;
        axios.post(`http://localhost:8090/employee/department/${departmentId}`,
                    employeeDetails,
                    {
                        headers : {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then( (response) => {
                                alert("Employee Added Successfully !!");
                                reset();
                                navigate("/admin/employees")
                            }
                )
                .catch( (response) => {
                                console.log("Error -> " + response.message);
                                alert("Employee Addition Unsucessful !!");
                            }
                )
    }

    function reset() {
        setName("");
        setEmail("");
        setPhone("");
        setSalary(0);
    }

}