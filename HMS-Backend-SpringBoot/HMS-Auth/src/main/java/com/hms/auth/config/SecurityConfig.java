package com.hms.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManagerBean(
						AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		String publicURL[] = {
				"/h2-console/**",
				"/swagger-ui/**",
				"/v3/api-docs/**"
		};
		
		http
		.cors()
		.and()
		.csrf().disable()
		.authorizeHttpRequests( (authRequest) -> {
				authRequest.requestMatchers(publicURL).permitAll();
				authRequest.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
				authRequest.requestMatchers("/customer/signup/**").permitAll();
				authRequest.requestMatchers("/customer/username/**").permitAll();
				authRequest.requestMatchers("/customer/signin/**","/customer/edit/**")
											.hasAuthority("ROLE_CUSTOMER");
				authRequest.requestMatchers("/admin/signin/**")
											.hasAuthority("ROLE_ADMIN");
				authRequest.anyRequest().authenticated();
		})
		.headers(t -> t.frameOptions().sameOrigin())
		.httpBasic()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authenticationProvider(daoAuthenticationProvider());
		
		return http.build();
    }
	
	//Default value for no. of rounds = 10
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/

}
