package com.hms.booking.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
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

import com.hms.booking.entity.Booking;
import com.hms.booking.entity.Customer;
import com.hms.booking.entity.MasterBookingDetails;
import com.hms.booking.entity.Room;
import com.hms.booking.entity.Tariff;
import com.hms.booking.feign.FeignCustomer;
import com.hms.booking.feign.FeignHotel;
import com.hms.booking.repository.BookingRepository;

@SuppressWarnings("deprecation")
@SpringBootTest
class BookingServiceTest {
	
	@Autowired
	private BookingService bookingService;
	
	@MockBean
	private FeignHotel feignHotel;
	
	@MockBean
	private FeignCustomer feignCustomer;
	
	@MockBean
	private BookingRepository bookingRepository;
	
	@BeforeEach
	void setUp() {
		
		Tariff tariff = new Tariff(1, "Sea Facing", "AC", 2, 2500);
		Room room1 = new Room(1, tariff);
		Room room2 = new Room(2, tariff);
		
		
		Booking booking1 = new Booking(
				1, "debmalyapal@gmail.com", 1, 
				new Date(2023,1,4), new Date(2023,1,8), "UPI", 2500);
		Booking booking2 = new Booking(
				2, "debmalyapal@gmail.com", 1, 
				new Date(2023,1,4), new Date(2023,1,8), "UPI", 2500);
		
		Customer customer = new Customer(
				1, "Debmalya Pal", "debmalyapal@gmail.com", "9062829894", "debmalya");
		
		Mockito.when(bookingRepository.findAll()).thenReturn(
									Arrays.asList(booking1, booking2));
		Mockito.when(bookingRepository.findByUsername("debmalyapal@gmail.com"))
				.thenReturn(Arrays.asList(booking1, booking2));
		
		Mockito.when(bookingRepository.findByRoomIdAndBookingDate(1, new Date(2023,1,10)))
				.thenReturn(Optional.empty());
		Mockito.when(bookingRepository.findByRoomIdAndBookingDate(1, new Date(2023,1,8)))
				.thenReturn(Optional.of(booking1));
		Mockito.when(bookingRepository.findByRoomIdAndBookingDate(2, new Date(2023,1,8)))
				.thenReturn(Optional.of(booking2));
		
		
		Mockito.when(feignCustomer.getCustomerByUsername("debmalyapal@gmail.com"))
				.thenReturn(ResponseEntity.ok().body(customer));
		Mockito.when(feignHotel.getRoomById(1))
				.thenReturn(ResponseEntity.ok().body(room1));
		Mockito.when(feignHotel.getRoomById(2))
				.thenReturn(ResponseEntity.ok().body(room2));
		Mockito.when(feignHotel.getRoomsByTariffId(0))
				.thenReturn(ResponseEntity.ok().body(Arrays.asList()));
		Mockito.when(feignHotel.getRoomsByTariffId(1))
				.thenReturn(ResponseEntity.ok().body(Arrays.asList(room1, room2)));
		
		
	}

	@Test
	void testGetAllBookings() {
		int expectedSize = 2;
		ResponseEntity<List<MasterBookingDetails>> bookingDetails =
											bookingService.getAllBookings();
		int actualSize = bookingDetails.getBody().size();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	void testGetBookingsByUsername() {
		int expectedSize = 2;
		ResponseEntity<List<MasterBookingDetails>> bookingDetails =
				bookingService.getBookingsByUsername("debmalyapal@gmail.com");
		int actualSize = bookingDetails.getBody().size();
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	void testGetAvailableRoomsByDate_Success() {
		int expectedRoomId = 1;
		ResponseEntity<Room> room = 
					bookingService.getAvailableRoomsByDate(1, new Date(2023,1,10));
		int actualRoomId = room.getBody().getRoomId();
		assertEquals(expectedRoomId, actualRoomId);
	}
	
	@Test
	void testGetAvailableRoomsByDate_Failure_WrongTariff() {
		int expectedStatusCode = ResponseEntity.notFound().build()
												.getStatusCode().value();
		ResponseEntity<Room> room = 
					bookingService.getAvailableRoomsByDate(0, new Date(2023,1,10));
		int actualStatusCode = room.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testGetAvailableRoomsByDate_Failure_AllRoomsBooked() {
		int expectedStatusCode = ResponseEntity.notFound().build()
												.getStatusCode().value();
		ResponseEntity<Room> room = 
					bookingService.getAvailableRoomsByDate(1, new Date(2023,1,8));
		int actualStatusCode = room.getStatusCode().value();
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testBookRoom() {
		Booking booking = new Booking(
				3, "debmalyapal@gmail.com", 1, 
				new Date(2023,1,4), new Date(2023,1,10), "Debit Card", 2500);
		Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
		ResponseEntity<MasterBookingDetails> bookingDetails = 
				bookingService.bookRoom(booking);
		int expectedBookingId = booking.getId();
		int actualBookingId = bookingDetails.getBody().getBookingId();
		assertEquals(expectedBookingId, actualBookingId);
	}

}
