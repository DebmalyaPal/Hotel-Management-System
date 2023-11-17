package com.hms.booking.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.booking.entity.Room;
import com.hms.booking.feign.FeignCustomer;
import com.hms.booking.feign.FeignHotel;
import com.hms.booking.entity.Booking;
import com.hms.booking.entity.Customer;
import com.hms.booking.entity.MasterBookingDetails;
import com.hms.booking.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private FeignHotel feignHotelService;
	
	@Autowired
	private FeignCustomer feignCustomerService;

	
	// Get All Bookings
	public ResponseEntity<List<MasterBookingDetails>> getAllBookings() {
		System.out.println("Fetching List of All Bookings...");
		List<Booking> bookingList = (List<Booking>)bookingRepository.findAll();
		List<MasterBookingDetails> bookingDetails =	new ArrayList<>();
		System.out.println("Requesting Auth & Hotel Service for each Customer's Bookings' data");
		for (Booking booking : bookingList) {
			MasterBookingDetails bookingRecord = 
										new MasterBookingDetails();
			bookingRecord.setBookingId(booking.getId());
			// Fetch customer from Auth Microservice using Feign Client
			// Customer customer = ..... and then set customer
			Customer customer = 
					feignCustomerService.getCustomerByUsername(
							booking.getUsername()).getBody();
			customer.setPassword(null);
			bookingRecord.setCustomer(customer);
			// Fetch Room from Hotel Microservice using Feign Client
			// Room room = ..... and then set room
			Room room = feignHotelService.getRoomById(
							booking.getRoomId()).getBody();
			bookingRecord.setRoom(room);
			bookingRecord.setTransactionDate(booking.getTransactionDate());
			bookingRecord.setBookingDate(booking.getBookingDate());
			bookingRecord.setPaymentMode(booking.getPaymentMode());
			bookingRecord.setAmount(booking.getAmount());
			bookingDetails.add(bookingRecord);
		}
		System.out.println("No. of Booking Records fetched = " + bookingList.size());
		return ResponseEntity.ok().body(bookingDetails);
	}
	
	// Get Bookings specific to a single User
	public ResponseEntity<List<MasterBookingDetails>> getBookingsByUsername(
												String username) {
		System.out.println("Fetching List of All Bookings for User with Email " + username);
		List<Booking> bookingList = 
				(List<Booking>)bookingRepository.findByUsername(username);
		List<MasterBookingDetails> bookingDetails =	new ArrayList<>();
		// Fetch customer from Auth Microservice using Feign Client
		// Customer customer = .....
		System.out.println("Requesting Auth & Hotel Service for Customer's Bookings' data");
		Customer customer = 
				feignCustomerService.getCustomerByUsername(username)
									.getBody();
		customer.setPassword(null);
		// Outside loop since customer common for all - use 'username'
		for (Booking booking : bookingList) {
			MasterBookingDetails bookingRecord = 
										new MasterBookingDetails();
			bookingRecord.setBookingId(booking.getId());
			// bookingRecord.setCustomer(customer)
			bookingRecord.setCustomer(customer);
			// Fetch Room from Hotel Microservice using Feign Client
			// Room room = ..... and bookingRecord.setRoom(room)
			Room room = feignHotelService.getRoomById(
								booking.getRoomId()).getBody();
			bookingRecord.setRoom(room);
			bookingRecord.setTransactionDate(booking.getTransactionDate());
			bookingRecord.setBookingDate(booking.getBookingDate());
			bookingRecord.setPaymentMode(booking.getPaymentMode());
			bookingRecord.setAmount(booking.getAmount());
			bookingDetails.add(bookingRecord);
		}
		System.out.println("No. of Bookings fetched = " + bookingList.size());
		return ResponseEntity.ok().body(bookingDetails);
	}

	// Find Available Rooms for a specific Date
	public ResponseEntity<Room> getAvailableRoomsByDate(
								int tariffId, Date date) {
		System.out.println("Fetching Available Rooms for tariff witd Id - " 
							+ tariffId + " on Date - " + date);
		//Fetch from Hotel Service using 'tariffId'
		List<Room> rooms = feignHotelService.getRoomsByTariffId(tariffId)
											.getBody();
		System.out.println(rooms);
		if (rooms.size() == 0) {
			System.out.println("No Room Found in the Tariff");
			return ResponseEntity.notFound().build();
		}
		else {
			for (Room room : rooms) {
				int roomId = room.getRoomId();
				Optional<Booking> booking = 
						bookingRepository.findByRoomIdAndBookingDate(
															roomId, date);
				if (booking.isPresent()) {
					System.out.println(room + " -> UNAVAIALBLE");
					continue;
				}
				else {
					System.out.println(roomId + " -> Room Avaialble");
					return ResponseEntity.ok().body(room);
				}
			}
			System.out.println("No Rooms Avaialble on Given tariff and Date");
			return ResponseEntity.notFound().build();
		}
	}
	
	// Book a Room for a specific Date
	public ResponseEntity<MasterBookingDetails> bookRoom(Booking booking) {
		System.out.println("Booking A Room...");
		bookingRepository.save(booking);
		MasterBookingDetails bookingDetails = new MasterBookingDetails();
		bookingDetails.setBookingId(booking.getId());
		// Fetch customer from Auth Microservice using Feign Client
		// Customer customer = ..... and set bookingDetails.setCustomer(customer)
		Customer customer = 
				feignCustomerService.getCustomerByUsername(
						booking.getUsername()).getBody();
		customer.setPassword(null);
		bookingDetails.setCustomer(customer);
		// Fetch Room from Hotel Microservice using Feign Client
		// Room room = ..... and set bookingDetails.setRoom(room);
		Room room = feignHotelService.getRoomById(
							booking.getRoomId()).getBody();
		bookingDetails.setRoom(room);
		bookingDetails.setTransactionDate(booking.getTransactionDate());
		bookingDetails.setBookingDate(booking.getBookingDate());
		bookingDetails.setPaymentMode(booking.getPaymentMode());
		bookingDetails.setAmount(booking.getAmount());
		System.out.println("Room Booked Successfully!!");
		return ResponseEntity.ok().body(bookingDetails);
	}	

}
