package com.hms.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hms.auth.entity.User;

public interface UserRepository 
				extends CrudRepository<User, Integer> {
	
	public Iterable<User> findByRole(String role);
	
	public Optional<User> findByEmail(String email);

}
