import './App.css';
import { Route, Routes } from 'react-router-dom';

import Login from './Components/Common/Login';
import Registration from './Components/Common/Registration';
import ErrorPage from './Components/Common/ErrorPage';
import Footer from './Components/Common/Footer';
import AdminHome from './Components/Admin/AdminHome';
import Tariff from './Components/Admin/Tariff';
import Employees from './Components/Admin/Employees';
import Department from './Components/Admin/Department';
import Bookings from './Components/Admin/Bookings';
import AddEmployee from './Components/Admin/AddEmployee';
import EditEmployee from './Components/Admin/EditEmployee';
import CustomerHome from './Components/Customer/CustomerHome';
import CustomerTariff from './Components/Customer/CustomerTariff';
import CustomerBookings from './Components/Customer/CustomerBookings';
import AuthenticatedRoute from './Components/Common/AuthenticatedRoute';
import ViewProfile from './Components/Customer/ViewProfile';
import EditProfile from './Components/Customer/EditProfile';
import EditTariff from './Components/Admin/EditTariff';
import ViewRooms from './Components/Admin/ViewRooms';
import ViewBooking from './Components/Admin/ViewBooking';
import ViewBookings from './Components/Customer/ViewBookings';
import BookRoom from './Components/Customer/BookRoom';

function App() {
  return (
    <div className="App">

      <Routes> 
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<Registration />} />

          <Route path="/customer" element={<AuthenticatedRoute/>}>
            <Route path="" element={<CustomerHome/>} exact/>
            <Route path="tariff" element={<CustomerTariff/>} exact/>
            <Route path="tariff/book" element={<BookRoom/>} exact/>
            <Route path="bookings" element={<CustomerBookings/>} exact/>
            <Route path="bookings/view" element={<ViewBookings/>} exact/>
            <Route path="profile/view" element={<ViewProfile/>} exact/>
            <Route path="profile/edit" element={<EditProfile/>} exact/>
          </Route>
          
          <Route path="/admin" element={<AuthenticatedRoute/>}>
            <Route path="" element={<AdminHome/>} exact/>
            <Route path='tariff' element={<Tariff/>} exact/>
            <Route path='tariff/edit' element={<EditTariff/>} exact/>
            <Route path="tariff/viewRooms" element={<ViewRooms/>} exact/>
            <Route path='department' element={<Department/>} exact/>
            <Route path='employees' element={<Employees/>} exact/>
            <Route path='addEmployee' element={<AddEmployee/>} exact/>
            <Route path='editEmployee' element={<EditEmployee/>} exact/>
            <Route path='bookings' element={<Bookings/>} exact/>
            <Route path='bookings/view' element={<ViewBooking/>} exact/>
          </Route>
          
          <Route path="/*" element={<ErrorPage/>}/>
      </Routes>

      <Footer/>

    </div>
  );
}

export default App;
