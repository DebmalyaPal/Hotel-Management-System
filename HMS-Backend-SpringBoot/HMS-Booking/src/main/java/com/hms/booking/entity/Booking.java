package com.hms.booking.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private int roomId;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date transactionDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date bookingDate;
	
	private String paymentMode;
	
	private int amount;

}
