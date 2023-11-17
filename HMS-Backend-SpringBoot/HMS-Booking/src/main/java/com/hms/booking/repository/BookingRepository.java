package com.hms.booking.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hms.booking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer>{
	
	public List<Booking> findByUsername(String usernameString);
	
	public Optional<Booking> findByRoomIdAndBookingDate(
								int roomId, Date bookingDate);

}
