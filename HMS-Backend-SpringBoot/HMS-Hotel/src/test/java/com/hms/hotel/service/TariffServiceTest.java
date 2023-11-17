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

import com.hms.hotel.entity.Tariff;
import com.hms.hotel.repository.TariffRepository;

@SpringBootTest
class TariffServiceTest {
	
	@Autowired
	private TariffService tariffService;
	
	@MockBean
	private TariffRepository tariffRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		Tariff tariff = new Tariff(1, "Sea-Facing", "AC", 2, 2500);
		Optional<Tariff> tariffOptional = Optional.of(tariff);
		Mockito.when(tariffRepository.findById(1)).thenReturn(tariffOptional);
		
		ArrayList<Tariff> tariffList = new ArrayList<>(
										Arrays.asList(
											new Tariff(1, "Sea-Facing", "AC", 2, 2500),
											new Tariff(2, "Sea-Facing", "AC", 4, 3500),
											new Tariff(3, "Non Sea-Facing", "AC", 2, 1500),
											new Tariff(4, "Non Sea-Facing", "AC", 4, 2500)
											)
										);
		Mockito.when(tariffRepository.findAll()).thenReturn(tariffList);
	}

	@Test
	void testGetAllTariff() {
		int expectedTariffListSize = 4;
		ResponseEntity<List<Tariff>> tariffList = tariffService.getAllTariffs();
		int actualTariffListSize = tariffList.getBody().size();
		assertEquals(expectedTariffListSize, actualTariffListSize);
	}
	
	
	@Test
	void testGetTariffById_Found() {
		int expectedTariffId = 1;
		ResponseEntity<Tariff> tariff = tariffService.getTariffById(1);
		int actualTariffId = tariff.getBody().getId();
		assertEquals(expectedTariffId, actualTariffId);
	}
	
	@Test
	void testGetTariffById_NotFound() {
		int expectedStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		ResponseEntity<Tariff> tariff = tariffService.getTariffById(0);
		int actualStatusCode = tariff.getStatusCode().value();
		System.out.println(expectedStatusCode + " " + actualStatusCode);
		assertEquals(expectedStatusCode, actualStatusCode);
	}
	
	@Test
	void testUpdateTariff_Success() {
		Tariff updatedTariff = new Tariff(1, "Sea-Facing", "AC", 2, 2499);
		ResponseEntity<Tariff> responseTariff = tariffService.updateTariff(1, updatedTariff);
		int expectedPrice = ResponseEntity.ok().build().getStatusCode().value();
		int actualPrice = responseTariff.getStatusCode().value();
		assertEquals(expectedPrice, actualPrice);
	}
	
	@Test
	void testUpdateTariff_Failure() {
		Tariff updatedTariff = new Tariff(0, "Sea-Facing", "AC", 2, 2499);
		ResponseEntity<Tariff> responseTariff = tariffService.updateTariff(0, updatedTariff);
		int expectedResponseStatusCode = ResponseEntity.notFound().build().getStatusCode().value();
		int actualResponseStatusCode = responseTariff.getStatusCode().value();
		assertEquals(expectedResponseStatusCode, actualResponseStatusCode);
	}
	
	

}
