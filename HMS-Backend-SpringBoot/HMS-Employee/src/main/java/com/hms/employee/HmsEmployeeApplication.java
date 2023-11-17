package com.hms.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HmsEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsEmployeeApplication.class, args);
	}

}
