package com.hackathon.stockservice.controllers;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.stockservice.dto.StockDTO;
import com.hackathon.stockservice.exceptions.FieldErrorDto;
import com.hackathon.stockservice.service.StockService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/stocks")
@Slf4j
public class StockController {

	/**
	 * Instance variable for stock service.
	 */
	@Autowired
	private StockService stockService;
	

	/**
	 * This method returns current price for the stock code..
	 * @param stockCode
	 * @return
	 */
	@ApiOperation(value = "Get the price of the stock", response = StockDTO.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = StockDTO.class),
					@ApiResponse(code = 404, message = "Stock not found", response = FieldErrorDto.class) })
	@GetMapping("/{stockCode}")
	public StockDTO getStrockPrice(@PathVariable("stockCode") @NotNull Optional<String> stockCode) {
		log.info("Getting stock price.");
		return stockService.getStockPrice(stockCode);
	}
}