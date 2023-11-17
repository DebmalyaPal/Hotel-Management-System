package com.hms.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.auth.entity.LoginRequest;
import com.hms.auth.entity.LoginResponse;
import com.hms.auth.entity.User;
import com.hms.auth.entity.Customer;
import com.hms.auth.repository.UserRepository;

@Service
public class CustomerService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<User> customerList = 
				(List<User>)userRepository.findByRole("ROLE_CUSTOMER");
		List<Customer> customerResponseList = new ArrayList<>();
		for (User customer : customerList) {
			customerResponseList.add(new Customer(customer.getId(), 
												customer.getName(), 
												customer.getEmail(), 
												customer.getPhone(),
												customer.getPassword()
												)
									);
		}
		return ResponseEntity.ok().body(customerResponseList);
	}

	
	//SIGN UP PROCESS
	public ResponseEntity<LoginResponse> registerCustomer(User user) {
		
		user.setRole("ROLE_CUSTOMER");
		String encryptedPassword = this.passwordEncoder.encode(
										user.getPassword());
		user.setPassword(encryptedPassword);
		System.out.println("SIGNING UP user -> " + user);
		userRepository.save(user);
		return ResponseEntity.ok()
							.body(new LoginResponse(user.getId(), user.getEmail()));
	}

	//SIGN IN PROCESS
	public ResponseEntity<LoginResponse> authenticateUser(
									LoginRequest loginRequest) {
		System.out.println("Autheticating User with Email Id - " 
							+ loginRequest.getEmail());
		Optional<User> optionalUser = this.userRepository
										.findByEmail(loginRequest.getEmail().trim());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (this.passwordEncoder.matches(loginRequest.getPassword(),
												user.getPassword())) {
				LoginResponse loginResponse = new LoginResponse(
												user.getId(), user.getEmail());
				System.out.println("SIGNING IN user -> " + user);
				return ResponseEntity.ok(loginResponse);
			}
			else {
				System.out.println("Wrong Password - " + loginRequest.getPassword());
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}
		else {
			System.out.println("No User found with Email (username) -> " 
									+ loginRequest.getEmail());
			return ResponseEntity.notFound().build();
		}
	}
	
	//Edit Customer Details
	public ResponseEntity<Customer> editCustomerInfo(String username, Customer customer) { 
		System.out.println("Editing User Info with Email Id - " + username);
		Optional<User> optionalUser = this.userRepository.findByEmail(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(customer.getName());
			user.setPhone(customer.getPhone());
			user.setPassword(this.passwordEncoder.encode(customer.getPassword()));
			user.setRole("ROLE_CUSTOMER");
			System.out.println("UPDATING User -> " + user);
			userRepository.save(user);
			return ResponseEntity.ok().body(customer);
		}
		else {
			System.out.println("No User found with Email Id - " + username);
			return ResponseEntity.notFound().build();			
		}
	}
	
	//Fetch Customer by Username
	public ResponseEntity<Customer> getCustomerByUsername(String username) {
		System.out.println("FETCHING User with Email -> " + username);
		Optional<User> optionalUser = userRepository.findByEmail(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			Customer customer = new Customer(user.getId(),
											user.getName(),
											user.getEmail(),
											user.getPhone(),
											user.getPassword());
			System.out.println("User -> " + user);
			return ResponseEntity.ok().body(customer);
		}
		System.out.println("User not found for Email -> " + username);
		return ResponseEntity.notFound().build();
	}
	
	/*
	public ResponseEntity<User> getCustomerById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		}
		return ResponseEntity.notFound().build();
	}
	*/

}
