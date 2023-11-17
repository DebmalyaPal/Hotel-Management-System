package com.hms.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hotel.entity.Tariff;
import com.hms.hotel.repository.TariffRepository;

@Service
public class TariffService {
	
	@Autowired
	private TariffRepository tariffRepository;
	
	// Get full list of tariffs
	public ResponseEntity<List<Tariff>> getAllTariffs() {
		System.out.println("FETCHING COMPLETE LIST OF TARIFF");
		List<Tariff> tariffList = (List<Tariff>)tariffRepository.findAll();
		System.out.println("ACTION SUCCESSFUL -> No. of Tariff Records - "
												+ tariffList.size());
		return ResponseEntity.ok().body(tariffList);
	}
	
	// Get a tariff using tariff-id
	public ResponseEntity<Tariff> getTariffById(int tariffId) {
		System.out.println("FETCHING TARIFF WITH ID - " + tariffId);
		Optional<Tariff> category = tariffRepository.findById(tariffId);
		if (category.isPresent()) {
			Tariff tariff = category.get();
			System.out.println("TARIFF with ID - " + tariffId
										+ " FETCHED SUCCESSFULLY");
			return ResponseEntity.ok().body(tariff);
		}
		else {
			System.out.println("NO TARIFF FOUND with ID - " + tariffId);
			return ResponseEntity.notFound().build();
		}
	}

	// Update a tariff (only price change)
	public ResponseEntity<Tariff> updateTariff(
						int tariffId, Tariff updatedTariff) {
		System.out.println("UPDATING TARIFF with ID - " + tariffId);
		Optional<Tariff> category = tariffRepository.findById(tariffId);
		if (category.isPresent()) {
			Tariff tariff = category.get();
			tariff.setPrice(updatedTariff.getPrice());
			tariffRepository.save(tariff);
			System.out.println("TARIFF with ID - " + tariffId
										+ " UPDATED SUCCESSFULLY");
			return ResponseEntity.ok().body(tariff);
		}
		else {
			System.out.println("NO TARIFF FOUND with ID - " + tariffId);
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * STRATEGY ->
			1st Delete all the rooms of this tariff then delete the tariff
		
	public Result deleteTariff(int tariffId) {
		System.out.println("Deleting Tariff with Id " + tariffId);	
		Optional<Tariff> tariff = tariffRepository.findById(tariffId);
		if (tariff.isPresent()) {
			tariffRepository.delete(tariff.get());
			return new Result(true, "Tariff Deleted Successfully!!");
		}
		return new Result(false, "Delete Unsuccessful! Tariff not present!");
	}
	*/

}
