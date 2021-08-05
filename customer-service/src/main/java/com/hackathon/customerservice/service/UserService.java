package com.hackathon.customerservice.service;

import static com.hackathon.customerservice.util.Error.INCORRECT_CREDENTIALS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.hackathon.customerservice.client.StockServiceProxy;
import com.hackathon.customerservice.dto.OrderRequestDto;
import com.hackathon.customerservice.dto.OrderResponseDto;
import com.hackathon.customerservice.dto.StockDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.OrderDetail;
import com.hackathon.customerservice.entity.PortfolioDetail;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.exceptions.InsufficientBalanceException;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.OrderDetailRepository;
import com.hackathon.customerservice.repository.PortfolioDetailRepository;
import com.hackathon.customerservice.repository.UserRepository;
import com.hackathon.customerservice.security.INGUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.hackathon.customerservice.util.Error.INCORRECT_CREDENTIALS;
import static com.hackathon.customerservice.util.Error.INSUFFICIENT_BALANCE;

@Service(value = "userService")
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private InvestmentAccountRepository investmentAccountRepository;

	@Autowired
	private PortfolioDetailRepository portfolioDetailRepository;

	@Autowired
	private StockServiceProxy serviceProxy;

	@Autowired
	private final OrderDetailRepository orderDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Processing login request");
		Optional<UserDetail> user = repository.findByCustomerId(username);
		if (!user.isPresent()) {
			log.error("Invalid username or password for user {}", username);
			throw new InvalidCredentialsException(INCORRECT_CREDENTIALS.getErrorMessage());
		} else {
			log.info("Login succeeded for user {}", username);
			UserDetail userDetail = user.get();
			List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority(userDetail.getUserRole().getRoleName()));
			return new INGUser(userDetail.getCustomerId(), userDetail.getPassword(), userDetail.getId(), authorityList);
		}
	}
	public OrderResponseDto orderstock(OrderRequestDto orderRequestDto, String accountNumber){
		Optional<InvestmentAccount> investmentAccount = investmentAccountRepository.findByAccountNumber(accountNumber);
		InvestmentAccount account = null;
		if(investmentAccount.isPresent())
			account = investmentAccount.get();
		StockDTO stockDTO = serviceProxy.getStrockPrice(orderRequestDto.getStockCode());
		double totalPrice = (double)orderRequestDto.getNumberOfStocks()*stockDTO.getPrice();
		if(account.getBalance() < totalPrice)
			throw new InsufficientBalanceException(INSUFFICIENT_BALANCE.getErrorMessage());
		Optional<PortfolioDetail> portfolioDetail = portfolioDetailRepository.findByInvestementAccount(account);
		PortfolioDetail detail = null;
		if(portfolioDetail.isPresent())
			detail = portfolioDetail.get();
		OrderDetail orderDetail = OrderDetail.builder()
				.quantity(orderRequestDto.getNumberOfStocks())
				.stockPrice(stockDTO.getPrice())
				.totalPrice(totalPrice)
				.purchasedOn(LocalDate.now())
				.stockCode(orderRequestDto.getStockCode())
				.portfolioDetail(detail).build();
		orderDetailRepository.save(orderDetail);
		return OrderResponseDto.builder()
				.statusCode(201)
				.message("You have bought the stocks").build();

	}

}