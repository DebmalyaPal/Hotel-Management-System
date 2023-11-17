package com.hms.hotel.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.hms.hotel.entity.Room;
import com.hms.hotel.entity.Tariff;
import com.hms.hotel.repository.RoomRepository;
import com.hms.hotel.repository.TariffRepository;

@SpringBootTest
class RoomServiceTest {

	@Autowired
	private RoomService roomService;
	
	@MockBean
	private RoomRepository roomRepository;
	
	@MockBean
	private TariffRepository tariffRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		Tariff tariff1 = new Tariff(1, "Sea-Facing", "AC", 2, 2500);
		Tariff tariff2 = new Tariff(2, "Sea-Facing", "AC", 4, 3500);
		Room room1 = new Room(1, tariff1);
		Room room2 = new Room(2, tariff1);
		Room room3 = new Room(3, tariff2);
		Room room4 = new Room(4, tariff2);
		List<Room> roomList = new ArrayList<>(Arrays.asList(room1, room2, room3, room4));
		List<Room> tariffOneRoomList = new ArrayList<>(Arrays.asList(room1, room2));
		tariff1.setRooms(tariffOneRoomList);
		
		Optional<Room> optionalRoom = Optional.of(room1);
		Mockito.when(roomRepository.findById(1)).thenReturn(optionalRoom);
		
		Mockito.when(roomRepository.findAll()).thenReturn(roomList);
		
		Mockito.when(tariffRepository.findById(1)).thenReturn(Optional.of(tariff1));
	}

	
	@Test
	void testGetAllRooms() {
		int expectedRoomListSize = 4;
		ResponseEntity<List<Room>> responseRoomList = roomService.getAllRooms();
		int actualRoomListSize = responseRoomList.getBody().size();
		assertEquals(expectedRoomListSize, actualRoomListSize);	
	}
	
	@Test
	void testGetRoomById_Success() {
		int expectedRoomId = 1;
		ResponseEntity<Room> responseRoom = roomService.getRoomById(1);
		int actualRoomId = responseRoom.getBody().getRoomId();
		assertEquals(expectedRoomId, actualRoomId);
	}
	
	@Test
	void testGetRoomById_Failure() {
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();;
		ResponseEntity<Room> responseRoom = roomService.getRoomById(0);
		int actualStatusCode = responseRoom.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testGetRoomsByTariffId_Success() {
		int expectedRoomListSize = 2;
		ResponseEntity<List<Room>> responseRoomList = roomService.getRoomsByTariffId(1);
		int actualRoomListSize = responseRoomList.getBody().size();
		System.out.println("Success Case -> " + responseRoomList.getBody());
		assertEquals(expectedRoomListSize, actualRoomListSize);	
	}
	
	@Test
	void testGetRoomsByTariffId_Failure() {
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		ResponseEntity<List<Room>> responseRoomList = roomService.getRoomsByTariffId(0);
		int actualStatusCode = responseRoomList.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);	
	}
	
	@Test
	void testAddRoomIntoTariff_Success() {
		int expectedStatusCode = ResponseEntity.ok().build().getStatusCode().value();
		Tariff tariff = new Tariff(1, "Sea-Facing", "AC", 2, 2500);
		Room room = new Room(5, tariff);
		ResponseEntity<Room> responseRoom = roomService.addRoomIntoTariff(1, room);
		int actualStatusCode = responseRoom.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);	
	}
	
	@Test
	void testAddRoomIntoTariff_Failure() {
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		Tariff tariff = new Tariff(3, "Sea-Facing", "Non-AC", 4, 1000);
		Room room = new Room(10, tariff);
		ResponseEntity<Room> responseRoom = roomService.addRoomIntoTariff(3, room);
		int actualStatusCode = responseRoom.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);	
	}

}
