package com.hms.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.auth.entity.LoginRequest;
import com.hms.auth.entity.LoginResponse;
import com.hms.auth.entity.User;
import com.hms.auth.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllAdmins() {
		return adminService.getAllAdmins();
	}
	
	//SIGN IN Process
	@PostMapping("/signin")
	public ResponseEntity<LoginResponse> autheticateAdmin(
						@RequestBody LoginRequest loginRequest) {
		return adminService.authenticateAdmin(loginRequest);
	}

}
