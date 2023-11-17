package com.hms.auth.service;

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
import com.hms.auth.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<List<User>> getAllAdmins() {
		List<User> adminList = 
				(List<User>)userRepository.findByRole("ROLE_ADMIN");
		return ResponseEntity.ok().body(adminList);
	}

	//SIGN IN PROCESS
	public ResponseEntity<LoginResponse> authenticateAdmin(LoginRequest loginRequest) {
		System.out.println("Autheticating User (Admin) with Email Id - " 
										+ loginRequest.getEmail());
		Optional<User> optionalUser = this.userRepository
									.findByEmail(loginRequest.getEmail().trim());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (this.passwordEncoder.matches(loginRequest.getPassword(),
												user.getPassword())) {
				LoginResponse loginResponse = new LoginResponse(
												user.getId(), user.getEmail());
				System.out.println("SIGNING IN Admin -> " + user);
				return ResponseEntity.ok().body(loginResponse);
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

}
