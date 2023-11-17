package com.hms.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Customer {

	private int userId;

	private String name;

	private String email;

	private String phone;

	private String password;

}