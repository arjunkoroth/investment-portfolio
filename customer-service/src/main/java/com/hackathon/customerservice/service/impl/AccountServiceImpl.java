package com.hackathon.customerservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.dto.PortfolioDetails;
import com.hackathon.customerservice.dto.StockDTO;
import com.hackathon.customerservice.dto.StockDetailsDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.PortfolioDetail;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;
import com.hackathon.customerservice.exceptions.StockNotFoundException;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.PortfolioDetailRepository;
import com.hackathon.customerservice.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	/**
	 * 
	 */
	@Autowired
	private InvestmentAccountRepository investmentAccountRepository;

	/**
	 * 
	 */
	@Autowired
	private PortfolioDetailRepository portfolioRepository;

	/**
	 * 
	 */
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<AccountDTO> getAccounts(String customerId) {
		log.info("Getting accounts.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hackathon.customerservice.service.AccountService#getPortfolio(java.util.
	 * Optional)
	 */
	public PortfolioDetails getPortfolio(Optional<String> accountNumber) {
		List<StockDetailsDTO> stocks = new ArrayList<>();
		PortfolioDetails result = PortfolioDetails.builder().balance(50000.0).build();
		if (log.isDebugEnabled()) {
			log.debug("Getting portfolio or account number {}", accountNumber);
		}
		Optional<InvestmentAccount> investmentAccount = investmentAccountRepository
				.findByAccountNumber(accountNumber.get());	
		if (investmentAccount.isPresent()) {
			List<PortfolioDetail> portfolios = portfolioRepository.findByInvestementAccount(investmentAccount.get());
			if (Optional.ofNullable(portfolios).isPresent()) {
				portfolios.forEach(portfolio -> {
					double currentStockPrice = getStockPrice(portfolio.getStockCode());
					double value = portfolio.getQuantity() * currentStockPrice;
				    double total = result.getTotalPortfolioValue() + value;
				    result.setTotalPortfolioValue(total);
					stocks.add(mapToStockDto(portfolio.getStockCode(), portfolio.getQuantity(), value));
				});
			}
		}else {
			throw new InvalidCredentialsException("User don't have permission to acccess this account.");
		}
		return result;
	}

	
	
	private Double getStockPrice(String stockCode) {
		Double result = Double.valueOf(0.00);
		try {
			ResponseEntity<StockDTO> stock = restTemplate.exchange("http://localhost:8082/api/v1/stocks/" + stockCode, HttpMethod.GET, null,
					StockDTO.class);
			result = stock.getBody().getStockPrice();
		} catch (StockNotFoundException ex) {
			throw new StockNotFoundException("Unable to process as the stock code is not present in the system.");
		}
		return result;
	}

	private StockDetailsDTO mapToStockDto(String stockCode, int quantity, double value) {
		return StockDetailsDTO.builder().stockCode(stockCode).quantity(quantity).value(value).build();
	}
}
