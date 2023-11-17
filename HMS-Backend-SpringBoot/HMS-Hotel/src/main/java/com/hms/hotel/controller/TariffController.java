package com.hms.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hotel.entity.Tariff;
import com.hms.hotel.service.TariffService;


@RestController
@RequestMapping("/tariff")
@CrossOrigin(origins = "http://localhost:3000")
public class TariffController {
	
	@Autowired
	private TariffService tariffService;
	
	
	// GET FULL TARIFF LIST
	@GetMapping("/")
	public ResponseEntity<List<Tariff>> getAllTariffs() {
		return tariffService.getAllTariffs();
	}
	
	// GET A TARIFF BY TARIFF ID
	@GetMapping("/{tariffId}")
	public ResponseEntity<Tariff> getTariffById(
							@PathVariable int tariffId) {
		return tariffService.getTariffById(tariffId);
	}
	
	// UPDATE PRICE OF A TARIFF
	@PutMapping("/{tariffId}")
	public ResponseEntity<Tariff> updateTariff(
			@PathVariable int tariffId, @RequestBody Tariff updatedTariff) {
		return tariffService.updateTariff(tariffId, updatedTariff);
	}
	
}