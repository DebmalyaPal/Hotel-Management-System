package com.hms.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hms.booking.entity.Customer;

@FeignClient(name = "HMS-AUTH-SERVICE", url = "http://localhost:9000")
public interface FeignCustomer {
	
	@GetMapping("/customer/username/{username}")
	ResponseEntity<Customer> getCustomerByUsername(
							@PathVariable String username);

}
