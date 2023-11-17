package com.hms.booking.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hms.booking.entity.Room;


@FeignClient(name = "HMS-HOTEL-SERVICE", url = "http://localhost:8080")
public interface FeignHotel {
	
	@GetMapping("/room/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable int roomId);
	
	@GetMapping("/room/tariff/{tariffId}")
	ResponseEntity<List<Room>> getRoomsByTariffId(@PathVariable int tariffId);

}
