package com.hms.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Tariff {
	
	@Id
	private int id;
	
	private String category;
	
	private String cooling; 
	
	private int occupancy;
	
	private int price;
	
	
	@OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Room> rooms = new ArrayList<>();
	
	public Tariff(int id, String category, String cooling, int occupancy, int price) {
		this.id = id;
		this.category = category;
		this.cooling = cooling;
		this.occupancy = occupancy;
		this.price = price;
	}
	
	public Tariff(String category, String cooling, int occupancy, int price) {
		this.category = category;
		this.cooling = cooling;
		this.occupancy = occupancy;
		this.price = price;
	}
	/*
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	*/
	
	
}
