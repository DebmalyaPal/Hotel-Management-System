package com.hms.booking.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.booking.entity.Booking;
import com.hms.booking.entity.MasterBookingDetails;
import com.hms.booking.entity.Room;
import com.hms.booking.service.BookingService;


@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	
	// GET ALL BOOKINGS
	@GetMapping("")
	public ResponseEntity<List<MasterBookingDetails>> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	// GET BOOKING BY USER NAME (EMAIL)
	@GetMapping("/user/{username}")
	public ResponseEntity<List<MasterBookingDetails>> getBookingsByUsername(
									@PathVariable String username) {
		return bookingService.getBookingsByUsername(username);
	}
	
	// GET ROOM AS PER AVAILABILITY FOR A GIVEN DATE
	@SuppressWarnings("deprecation")
	@GetMapping("/tariff/{tariffId}/date/{date}/")
	public ResponseEntity<Room> getAvailableRoomsByDate(
			@PathVariable int tariffId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		return bookingService.getAvailableRoomsByDate(tariffId, new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
	}
	
	// BOOK A ROOM
	@PostMapping("")
	public ResponseEntity<MasterBookingDetails> bookRoom(
							@RequestBody Booking booking) {
		return bookingService.bookRoom(booking);
	}
		

}
