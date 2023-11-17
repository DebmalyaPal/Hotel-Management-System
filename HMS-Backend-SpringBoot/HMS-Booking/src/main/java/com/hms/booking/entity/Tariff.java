package com.hms.booking.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Tariff {
	
	private int id;
	
	private String category;
	
	private String cooling; 
	
	private int occupancy;
	
	private int price;
	
}