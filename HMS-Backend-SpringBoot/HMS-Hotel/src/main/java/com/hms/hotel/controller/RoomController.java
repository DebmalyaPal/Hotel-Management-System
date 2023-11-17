package com.hms.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hotel.entity.Room;
import com.hms.hotel.service.RoomService;


@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	
	// GET ALL ROOMS
	@GetMapping("/")
	public ResponseEntity<List<Room>> getAllRooms() {
		return roomService.getAllRooms();
	}
	
	// GET A ROOM BY ROOM-ID
	@GetMapping("/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable int roomId) {
		return roomService.getRoomById(roomId);
	}
	
	// GET ROOMS BY TARIFF-ID
	@GetMapping("/tariff/{tariffId}")
	public ResponseEntity<List<Room>> getRoomsByTariffId(
									@PathVariable int tariffId) {
		return roomService.getRoomsByTariffId(tariffId);
	}
	
	// ADD A ROOM UNDER A TARIFF
	@PostMapping("/tariff/{tariffId}")
	public ResponseEntity<Room> addRoomIntoTariff(
			@PathVariable int tariffId, @RequestBody Room room) {
		return roomService.addRoomIntoTariff(tariffId, room);
	}

}
