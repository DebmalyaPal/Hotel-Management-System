package com.hms.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hms.auth.entity.LoginRequest;
import com.hms.auth.entity.LoginResponse;
import com.hms.auth.entity.User;
import com.hms.auth.repository.UserRepository;

@SpringBootTest
class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		User admin1 = new User("Debmalya Pal", "debmalyapal@gmail.com", "9062829894",
								passwordEncoder.encode("debmalya.pal"));
		admin1.setRole("ROLE_ADMIN");
		User admin2 = new User("Admin Test", "admin@yahoo.com", "9988112299",
								passwordEncoder.encode("admin.test"));
		admin2.setRole("ROLE_ADMIN");
		List<User> adminList = new ArrayList<>(Arrays.asList(admin1, admin2));
		
		Mockito.when(userRepository.findByRole("ROLE_ADMIN")).thenReturn(adminList);
		Mockito.when(userRepository.findByEmail("debmalyapal@gmail.com"))
			.thenReturn(Optional.of(admin1));
	}
	
	
	@Test
	void testGetAllAdmins() {
		int expectedAdminListSize = 2;
		ResponseEntity<List<User>> responseAdminList = adminService.getAllAdmins();
		int actualAdminListSize = responseAdminList.getBody().size();
		assertEquals(expectedAdminListSize, actualAdminListSize);
	}

	@Test
	void testAutheticateAdmin_Success() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@gmail.com", "debmalya.pal");
		String expectedUsername = loginRequest.getEmail();
		ResponseEntity<LoginResponse> loginResponse = 
					adminService.authenticateAdmin(loginRequest);
		String actualUsername = loginResponse.getBody().getEmail();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testAutheticateAdmin_Failure_IncorrectUsername() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@yahoo.com", "debmalya.pal");
		int expectedStatusCode = ResponseEntity.notFound().build()
											.getStatusCode().value();
		ResponseEntity<LoginResponse> loginResponse = 
					adminService.authenticateAdmin(loginRequest);
		int actualStatusCode = loginResponse.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testAutheticateAdmin_Failure_IncorrectPassword() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@gmail.com", "debmalya");
		int expectedStatusCode = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
											.getStatusCode().value();
		ResponseEntity<LoginResponse> loginResponse = 
					adminService.authenticateAdmin(loginRequest);
		int actualStatusCode = loginResponse.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}

}
