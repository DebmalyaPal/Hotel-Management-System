package com.hms.auth.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserNotFoundException extends Exception {
	
	private String username;
	
	public UserNotFoundException(String username) {
		super("No User Found with Email - " + username);
		this.username = username;
	}

}
