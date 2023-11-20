import React, { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import AdminHeader from "./AdminHeader";

export default function EditEmployee() {

    const navigate = useNavigate();

    const location = useLocation();
    const [employee] = useState(location.state.employee);
    
    const [id] = useState(employee.employeeId)
    const [name, setName] = useState(employee.name);
    /*const [department, setDepartment] = useState(employee.department);*/
    const [email, setEmail] = useState(employee.email);
    const [phone, setPhone] = useState(employee.phone);
    const [salary, setSalary] = useState(employee.salary);


    return(
        <>
            <AdminHeader/>
            <form className="mt-3 pt-4">
                <table className="table table-borderless bg-light mx-auto rounded-3"
                        style={{width:"40%"}}>
                    <thead>
                        <tr>
                            <th colSpan={2}>
                                <h3>EMPLOYEE DETAILS</h3>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="text-end">Employee Id : </td>
                            <td>{ id }</td>
                        </tr>
                        <tr>
                            <td className="text-end">Name : </td>
                            <td className="text-center">
                                <input type="text" name="name" value={ name }
                                        onChange={ (e) => {setName(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end"> Department : </td>
                            <td className="text-center"> { employee.department.departmentName } </td>
                        </tr>
                        <tr>
                            <td className="text-end">Email Id : </td>
                            <td>
                                <input type="email" name="email" value={ email }
                                        onChange={ (e) => {setEmail(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end"> Phone No. :</td>
                            <td>
                                <input type="tel" name="phone" value={ phone }
                                        onChange={ (e) => {setPhone(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td className="text-end">Salary (in INR) : </td>
                            <td>
                                <input type="number" name="salary" value={ salary }
                                        onChange={ (e) => {setSalary(e.target.value)} } />
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className="btn btn-info"
                                        style={{width:"50%"}}
                                        onClick={ updateEmployee }>
                                    UPDATE
                                </button>    
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </>
    )


    function updateEmployee(event) {
        event.preventDefault();
        var employeeDetails = {
            name : name.trim(),
            email : email.trim(),
            phone : phone.trim(),
            salary : Number(salary.trim())
        };
        var employeeId = Number(id);
        console.log(employeeId);
        console.log(employeeDetails);
        axios.put(`http://localhost:8090/employee/${employeeId}`,
                    employeeDetails,
                    {
                        headers : {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then( (response) => {
                                alert("Employee Details Updated Successfully !!");
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