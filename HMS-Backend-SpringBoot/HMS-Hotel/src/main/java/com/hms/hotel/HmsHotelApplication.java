package com.hms.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HmsHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsHotelApplication.class, args);
	}

}
