package com.hackathon.stockservice.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.hackathon.stockservice.StockServiceApplication;
import com.hackathon.stockservice.dto.StockDTO;
import com.hackathon.stockservice.service.StockServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StockController.class)
@ContextConfiguration(classes = StockServiceApplication.class)
public class StockControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockController controller;

	@Mock
	private StockServiceImpl service;

	@Test
	void testGetStockPrice() throws Exception {
		when(service.getStockPrice(Optional.of("APPL"))).thenReturn(StockDTO.builder().stockPrice(10.0).build());
		mockMvc.perform(get("/api/v1/stocks/APPL")).andExpect(status().isOk())
				.andExpect(jsonPath("$.stockPrice", Matchers.is(10.0)));
	}

	@Test
	void testGetStockPrice_Exception() throws Exception {
		when(service.getStockPrice(Optional.of("APPL"))).thenReturn(StockDTO.builder().stockPrice(10.0).build());
		mockMvc.perform(get("/api/v1/stocks/APPL")).andExpect(status().isNotFound());
	}
}
