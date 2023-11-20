import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";


export default function Employees() {

    useEffect( () => { displayEmployees(); }, []);

    const navigate = useNavigate();

    const [employees, setEmployees] = useState([]);

    return (
        <>
            <AdminHeader></AdminHeader>
            <table className="table table-dark table-striped mx-auto"
                style={{width:"80%", marginTop:"10px"}}>
                <thead>
                    <tr>
                        <th colSpan={7}>
                            <h4>EMPLOYEES</h4>
                        </th>
                    </tr>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email Id</th>
                        <th>Phone No.</th>
                        <th>Department</th>
                        <th>Salary (in Rs.)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(
                            employee => 
                                <tr key={ employee.employeeId }>
                                    <td>{ employee.employeeId }</td>
                                    <td>{ employee.name }</td>
                                    <td>{ employee.email }</td>
                                    <td>{ employee.phone }</td>
                                    <td>{ employee.department.departmentName }</td>
                                    <td>{ employee.salary + ".00 INR"}</td>
                                    <td>
                                        <button className="btn btn-primary btn-sm me-1"
                                                onClick={ () => { editEmployee(employee) } }> 
                                            &nbsp;&nbsp;
                                            Edit
                                            &nbsp;&nbsp;
                                        </button>
                                        <button className="btn btn-primary btn-sm"
                                                onClick={ () => { deleteEmployee(employee.employeeId) }}>
                                            Delete 
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                </tbody>
            </table>
        </>
    )


    function displayEmployees() {
        axios.get('http://localhost:8090/employee/')
            .then( response => setEmployees(response.data) )
            .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

    function deleteEmployee(employeeId) {
        console.log(employeeId);
        axios.delete(`http://localhost:8090/employee/${employeeId}`)
        .then( response => { 
                    alert("Employee Deleted Successfully !!!");
                    displayEmployees();
                })
        .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

    function editEmployee(employee) {
        console.log(employee)
        navigate("/admin/editEmployee", {state : { employee }})
    }

}



/*
import React, { Component } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";


class Employees extends Component {

    constructor(props) {
        super(props)

        this.state = {
            employees : []
        }

        this.displayEmployees = this.displayEmployees.bind(this)
        this.deleteEmployee = this.deleteEmployee.bind(this)
    }

    componentDidMount() {
        this.displayEmployees()
    }

    render() {
        return (
            <>
                <AdminHeader></AdminHeader>
                <table className="table table-dark table-striped mx-auto"
                    style={{width:"75%", marginTop:"5px"}}>
                    <thead>
                        <tr>
                            <th colSpan={6}>
                                <h4>EMPLOYEES</h4>
                            </th>
                        </tr>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Email Id</th>
                            <th>Department</th>
                            <th>Salary (in Rs.)</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.employees.map(
                                employee => 
                                    <tr key={employee.employeeId.toString()}>
                                        <td>{ employee.employeeId }</td>
                                        <td>{ employee.name }</td>
                                        <td>{ employee.email }</td>
                                        <td>{ employee.department.departmentName }</td>
                                        <td>{ employee.salary + ".00 INR"}</td>
                                        <td>
                                            <button className="btn btn-primary btn-sm me-1"> 
                                                &nbsp;&nbsp;
                                                Edit
                                                &nbsp;&nbsp;
                                            </button>
                                            <button className="btn btn-primary btn-sm"
                                                    onClick={ () => {this.deleteEmployee(employee.employeeId)}}>
                                                Delete 
                                            </button>
                                        </td>
                                    </tr>
                                )
                            }
                    </tbody>
                </table>
            </>
        )
    }



    displayEmployees() {
        axios.get('http://localhost:8090/employee/')
            .then( response => this.setState({employees : response.data}) )
            .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

    deleteEmployee(employeeId) {
        axios.delete(`http://localhost:8090/employee/${employeeId}`)
        .then( response => { 
                    alert("Employee Deleted Successfully !!!");
                    this.displayEmployees();
                })
        .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

}

export default Employees;
*/
