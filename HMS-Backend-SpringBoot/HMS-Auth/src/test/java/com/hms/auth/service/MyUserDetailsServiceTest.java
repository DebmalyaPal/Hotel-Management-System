package com.hms.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hms.auth.config.MyUserDetailsService;
import com.hms.auth.entity.User;
import com.hms.auth.repository.UserRepository;


@SpringBootTest
class MyUserDetailsServiceTest {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private UserRepository userRepository;
	

	@BeforeEach
	void setUp() {
		User customer = new User("Debmalya Pal", "debmalyapal@gmail.com", "9062829894",
								passwordEncoder.encode("debmalya.pal"));
		Mockito.when(userRepository.findByEmail("debmalyapal@gmail.com"))
			.thenReturn(Optional.of(customer));
	}
	
	@Test
	void testLoadUserByUsername_Success() {
		String expectedUsername = "debmalyapal@gmail.com";
		UserDetails user = myUserDetailsService.loadUserByUsername(expectedUsername);
		String actualUsername = user.getUsername();
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Test
	void testLoadUserByUsername_Failure() {
		String username = "debmalyapal@yahoo.com";
		Exception exception = 
				assertThrows(UsernameNotFoundException.class, 
							() -> {
									myUserDetailsService
									.loadUserByUsername("debmalyapal@yahoo.com");
								}
							);
		System.out.println(exception.getMessage());
		String expectedResponseMessage = "No User Found with Email - " + username;
		String actualResponseMessage = exception.getMessage();
		assertEquals(expectedResponseMessage, actualResponseMessage);
	}

}
