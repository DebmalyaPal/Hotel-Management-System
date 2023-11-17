package com.hms.auth.entity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "username")
	private String email;
	
	private String role;
	
	private String phone;
	
	private String password;
	
	
	/*
	@JsonIgnore
	private boolean enabled;
	@JsonIgnore
	private Collection<GrantedAuthority> authorities;
	@JsonIgnore
	private boolean accountNonLocked;
	@JsonIgnore
	private boolean credentialsNonExpired;
	@JsonIgnore
	private boolean accountNonExpired;
	*/
	
	// Constructor without id (Auto Generated)
	public User(String name, String email, String phone, String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>(
						Arrays.asList(
								new SimpleGrantedAuthority(this.role)
								)
						);
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
