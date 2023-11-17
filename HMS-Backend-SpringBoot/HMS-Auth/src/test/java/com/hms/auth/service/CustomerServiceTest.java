package com.hms.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.hms.auth.entity.Customer;
import com.hms.auth.entity.LoginRequest;
import com.hms.auth.entity.LoginResponse;
import com.hms.auth.entity.User;
import com.hms.auth.repository.UserRepository;


@SpringBootTest
class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private UserRepository userRepository;
	

	@BeforeEach
	void setUp() {
		User customer1 = new User("Debmalya Pal", "debmalyapal@gmail.com", "9062829894",
								passwordEncoder.encode("debmalya.pal"));
		customer1.setRole("ROLE_CUSTOMER");
		User customer2 = new User("Customer Test", "customer@yahoo.com", "9988112299",
								passwordEncoder.encode("customer.test"));
		customer2.setRole("ROLE_CUSTOMER");
		List<User> customerList = new ArrayList<>(Arrays.asList(customer1, customer2));
		
		Mockito.when(userRepository.findByRole("ROLE_CUSTOMER")).thenReturn(customerList);
		Mockito.when(userRepository.findByEmail("debmalyapal@gmail.com"))
			.thenReturn(Optional.of(customer1));
	}
	
	@Test
	void testGetAllCustomers() {
		int expectedCustomerListSize = 2;
		ResponseEntity<List<Customer>> customerList = customerService.getAllCustomers();
		int actualCustomerListSize = customerList.getBody().size();
		assertEquals(expectedCustomerListSize, actualCustomerListSize);;
	}
	
	@Test
	void testGetCustomerByUsername_Success() {
		String expectedUsername = "debmalyapal@gmail.com";
		ResponseEntity<Customer> responseCustomer = 
				customerService.getCustomerByUsername(expectedUsername);
		String actualUsername = responseCustomer.getBody().getEmail();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testGetCustomerByUsername_Failure() {
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
													.getStatusCode().value();
		ResponseEntity<Customer> responseCustomer = 
				customerService.getCustomerByUsername("debmalyapal@yahoo.com");
		int actualResponseStatusCode = responseCustomer.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}
	
	@Test
	void testRegisterCustomer() {
		User customer = new User("User Customer", "customer@gmail.com", "9876543210",
								passwordEncoder.encode("user.customer"));
		customer.setRole("ROLE_CUSTOMER");
		Mockito.when(userRepository.save(customer)).thenReturn(customer);
		ResponseEntity<LoginResponse> responseRegisterCustomer
						= customerService.registerCustomer(customer);
		String expectedUsername = customer.getEmail();
		String actualUsername = responseRegisterCustomer.getBody().getEmail();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testAutheticateUser_Success() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@gmail.com", "debmalya.pal");
		String expectedUsername = loginRequest.getEmail();
		ResponseEntity<LoginResponse> loginResponse = 
					customerService.authenticateUser(loginRequest);
		String actualUsername = loginResponse.getBody().getEmail();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testAutheticateUser_Failure_IncorrectUsername() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@yahoo.com", "debmalya.pal");
		int expectedStatusCode = ResponseEntity.notFound().build()
											.getStatusCode().value();
		ResponseEntity<LoginResponse> loginResponse = 
					customerService.authenticateUser(loginRequest);
		int actualStatusCode = loginResponse.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testAutheticateUser_Failure_IncorrectPassword() {
		LoginRequest loginRequest = 
				new LoginRequest("debmalyapal@gmail.com", "debmalya");
		int expectedStatusCode = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
											.getStatusCode().value();
		ResponseEntity<LoginResponse> loginResponse = 
					customerService.authenticateUser(loginRequest);
		int actualStatusCode = loginResponse.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testEditCustomerInfo_Success() {
		Customer customer = new Customer(1, "Debmalya Pal", "debmalyapal@gmail.com", "8902462312",
										passwordEncoder.encode("debmalyapal"));
		//Mockito.when(userRepository.save(customer)).thenReturn(customer);
		String expectedUsername = customer.getEmail();
		ResponseEntity<Customer> responseCustomer = customerService.editCustomerInfo(expectedUsername, customer);
		String actualUsername = responseCustomer.getBody().getEmail();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testEditCustomerInfo_Failure() {
		Customer customer = new Customer(1, "Debmalya", "debmalya@yahoo.com", "8902462312",
										passwordEncoder.encode("debmalyapal"));
		//Mockito.when(userRepository.save(customer)).thenReturn(customer);
		int expectedResponseStatusCode = ResponseEntity.notFound().build()
													.getStatusCode().value();
		ResponseEntity<Customer> responseCustomer = 
				customerService.editCustomerInfo(customer.getEmail(), customer);
		int actualResponseStatusCode = responseCustomer.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}

}
