import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";

export default function Department(props) {

    const navigate = useNavigate();
    
    const [departments, setDepartments] = useState([]);

    useEffect( () => { displayDepartment(); }, []);

    return (
        <div>
            <AdminHeader/>
            <table className="table table-dark table-striped mx-auto"
                    style={{width:"50%", marginTop:"5px"}}>
                <thead>
                    <tr>
                        <th colSpan={3}>
                            <h4>DEPARTMENT</h4>
                        </th>
                    </tr>
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        departments.map(
                            department => 
                                <tr key={ department.departmentId }>
                                    <td>{ department.departmentId }</td>
                                        <td>{ department.departmentName }</td>
                                        <td>
                                            <button className="btn btn-primary btn-sm"
                                                    onClick={ () => { addEmployeeByDepartment(department) } }>
                                                Add Employee
                                            </button>
                                        </td>
                                    </tr>
                                )
                    }
                </tbody>
            </table>
        </div>
    )


    function displayDepartment() {
        axios.get('http://localhost:8090/department/')
            .then( response => { setDepartments(response.data) } )
            .catch( response => console.log("ERROR fetching Tariff -> " + response));
    }

    function addEmployeeByDepartment(department) {
        navigate("/admin/AddEmployee", { state : {department}});
    }

}




/*
import React, { Component } from "react";
import axios from "axios";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import AdminHeader from "./AdminHeader";

class Department extends Component {

    constructor(props) {
        super(props)

        this.state = {
            departments : []
        }

        this.displayDepartment = this.displayDepartment.bind(this)
    }

    componentDidMount() {
        this.displayDepartment()
    }

    render() {
        return (
            <>
                <AdminHeader></AdminHeader>
                <table className="table table-dark table-striped mx-auto"
                    style={{width:"60%", marginTop:"5px"}}>
                    <thead>
                        <tr>
                            <th colSpan={3}>
                                <h4>DEPARTMENT</h4>
                            </th>
                        </tr>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.departments.map(
                                department => 
                                    <tr key={department.departmentId}>
                                        <td>{ department.departmentId }</td>
                                        <td>{ department.departmentName }</td>
                                        <td>
                                            <button className="btn btn-primary btn-sm">Add Employee</button>
                                        </td>
                                    </tr>
                                )
                            }
                    </tbody>
                </table>
            </>
        )
    }



    displayDepartment() {
        axios.get('http://localhost:8090/department/')
            .then( response => this.setState({ departments : response.data }) )
            .catch( response => console.log("ERROR fetching Tariff -> " + response));
        
        return;
    }

}

export default Department;
*/
