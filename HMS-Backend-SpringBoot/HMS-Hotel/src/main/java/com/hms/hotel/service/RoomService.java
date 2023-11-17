package com.hms.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hotel.entity.Room;
import com.hms.hotel.entity.Tariff;
import com.hms.hotel.repository.RoomRepository;
import com.hms.hotel.repository.TariffRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private TariffRepository tariffRepository;

	
	// Get list of all rooms
	public ResponseEntity<List<Room>> getAllRooms() {
		System.out.println("FETCHING COMPLETE LIST OF ROOMS");
		List<Room> roomList = (List<Room>)roomRepository.findAll();
		System.out.println("ACTION SUCCESSFUL -> No. of Tariff Records - "
												+ roomList.size());
		return ResponseEntity.ok().body(roomList);
	}

	// Get a room by room id
	public ResponseEntity<Room> getRoomById(int roomId) {
		System.out.println("FETCHING ROOM with ID - " + roomId);
		Optional<Room> optionalRoom = roomRepository.findById(roomId);
		if (optionalRoom.isPresent()) {
			Room room = optionalRoom.get();
			System.out.println("ROOM with ID - " + roomId + " FETCHED SUCCESSFULLY");
			return ResponseEntity.ok().body(room);
		}
		else {
			System.out.println("NO ROOM FOUND with ID - " + roomId);
			return ResponseEntity.notFound().build();
		}
	}
	
	// Get list of rooms in a specific tariff
	public ResponseEntity<List<Room>> getRoomsByTariffId(int tariffId) {
		System.out.println("FETCHING LIST OF ROOMS IN TARIFF with ID - " + tariffId);
		Optional<Tariff> optionalTariff = tariffRepository.findById(tariffId);
		if (optionalTariff.isPresent()) {
			Tariff tariff = optionalTariff.get();
			List<Room> roomList = tariff.getRooms();
			System.out.println(roomList.size() + " ROOMS FOUND UNDER "
								+ "TARIFF with ID - " + tariffId);
			return ResponseEntity.ok().body(roomList);
		}
		else {
			System.out.println("NO ROOMS FOUND UNDER TARIFF with ID - " 
																+ tariffId);
			return ResponseEntity.notFound().build();
		}
	}
	
	// Add a room in a specific tariff
	public ResponseEntity<Room> addRoomIntoTariff(
					int tariffId, Room newRoom) {
		System.out.println("ADDING A ROOM TO TARIFF with ID - " + tariffId);
		Optional<Tariff> optionalTariff = tariffRepository.findById(tariffId);
		if (optionalTariff.isPresent()) {
			Tariff tariff = optionalTariff.get();
			Room room = new Room();
			room.setTariff(tariff);
			roomRepository.save(room);
			System.out.println("ROOM ADDED SUCCESSFULLY TO TARIFF with ID - " 
																	+ tariffId);
			return ResponseEntity.ok().body(room);
		}
		else {
			System.out.println("ROOM ADDITION FAILED! NO TARIFF FOUND with ID - " 
																	+ tariffId);
			return ResponseEntity.notFound().build();
		}
	}

	
}
