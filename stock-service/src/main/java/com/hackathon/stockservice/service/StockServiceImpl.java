package com.hackathon.stockservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.stockservice.dto.StockDTO;
import com.hackathon.stockservice.entity.StockDetail;
import com.hackathon.stockservice.exceptions.StockNotFoundException;
import com.hackathon.stockservice.repository.StockRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class will have operation related to get the stock price.
 * @author Team-1.
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	/**
	 * Instance variable for stockRepository
	 */
	@Autowired
	private final StockRepository stockRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackathon.stockservice.service.StockService#getStockPrice(int)
	 */
	public StockDTO getStockPrice(Optional<String> stockCode) {
		log.debug("Getting stock price for {}", stockCode);
		StockDTO stockDTO = null;
		Optional<StockDetail> stockDetail = stockRepository.findByStockCode(stockCode.get());
		if (stockDetail.isPresent()) {
			stockDTO = StockDTO.builder().stockCode(stockCode.get()).stockPrice(generatePrice()).build();
		} else {
			throw new StockNotFoundException("Stock not found.");
		}
		return stockDTO;
	}

	/**
	 * Generates a random number for a stock.
	 * 
	 * @return
	 */
	private double generatePrice() {
		log.info("Generating price.");
		double leftLimit = 1D;
		double rightLimit = 10D;
		double number = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);		
		BigDecimal format = BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP);
		return format.doubleValue();
	}
}