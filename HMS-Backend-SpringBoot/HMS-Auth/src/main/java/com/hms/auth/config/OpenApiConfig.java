package com.hms.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

	@Bean
	public OpenAPI baseOpenAPI() {
		String title = "HOTEL MANAGEMENT SYSTEM - AUTH SERVICE";
		String description = "Authentication & Authorization Section";
		return new OpenAPI().info(new Info().title(title)
											.version("1.0.0")
											.description(description));
	}

}