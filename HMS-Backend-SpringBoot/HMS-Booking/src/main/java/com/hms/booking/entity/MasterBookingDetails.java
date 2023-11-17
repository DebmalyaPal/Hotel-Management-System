package com.hms.booking.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class MasterBookingDetails {
	
	private int bookingId;
	
	private Customer customer;
	private Room room;

	private Date transactionDate;
	private Date bookingDate;
	
	private String paymentMode;
	private int amount;
	
}
