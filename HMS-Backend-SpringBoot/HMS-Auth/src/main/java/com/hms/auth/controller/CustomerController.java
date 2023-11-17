package com.hms.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.auth.entity.LoginRequest;
import com.hms.auth.entity.LoginResponse;
import com.hms.auth.entity.User;
import com.hms.auth.entity.Customer;
import com.hms.auth.service.CustomerService;

@RestController()
@RequestMapping(path = "/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//CUSTOMER SIGN UP
	@PostMapping("/signup")
	public ResponseEntity<LoginResponse> registerCustomer(@RequestBody User user) {
		return customerService.registerCustomer(user);
	}
	
	//CUSTOMER SIGN IN
	@PostMapping("/signin")
	public ResponseEntity<LoginResponse> autheticateUser(
							@RequestBody LoginRequest loginRequest) {
		return customerService.authenticateUser(loginRequest);
	}
	
	//VIEW CUSTOMER BY USERNAME
	@GetMapping("/username/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(
										@PathVariable String username) {
		return this.customerService.getCustomerByUsername(username);
	}
	
	//EDIT CUSTOMER'S EXISTING DETAILS
	@PutMapping("/edit/{username}")
	public ResponseEntity<Customer> editCustomerInfo(
									@PathVariable String username,
									@RequestBody Customer customer) {
		return this.customerService.editCustomerInfo(username, customer);
	}
	
	
	//FETCH or VIEW ALL CUSTOMERS
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return this.customerService.getAllCustomers();
	}
	
	/*
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserById(@PathVariable("username") String id) {
		return userService.getCustomerById(id);
	}
	*/

}
