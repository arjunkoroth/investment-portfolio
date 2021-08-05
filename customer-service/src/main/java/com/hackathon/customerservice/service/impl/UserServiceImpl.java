package com.hackathon.customerservice.service.impl;

import com.hackathon.customerservice.client.StockServiceProxy;
import com.hackathon.customerservice.dto.OrderRequestDto;
import com.hackathon.customerservice.dto.OrderResponseDto;
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
import lombok.RequiredArgsConstructor;
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

import static com.hackathon.customerservice.util.Error.*;

@Service(value = "userService")
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private final UserRepository repository;

	@Autowired
	private final InvestmentAccountRepository investmentAccountRepository;

	@Autowired
	private final PortfolioDetailRepository portfolioDetailRepository;

	@Autowired
	private final StockServiceProxy serviceProxy;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		List<UserDetail> userList = repository.findByCustomerId(username);
		if (userList.isEmpty()) {
			log.error("Invalid username or password for user {}", username);
			throw new InvalidCredentialsException("Invalid username or password");
		} else {
			UserDetail user = userList.stream().findFirst().get();
			List<SimpleGrantedAuthority> authorityList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			return new INGUser(user.getCustomerId(), user.getPassword(), user.getId(), authorityList);
		}
	}
	public OrderResponseDto orderstock(OrderRequestDto orderRequestDto, String accountNumber){
		Optional<InvestmentAccount> investmentAccount = investmentAccountRepository.findByAccountNumber(accountNumber);
		InvestmentAccount account = null;
		if(investmentAccount.isPresent())
			account = investmentAccount.get();
		double price = serviceProxy.getStockPrice(orderRequestDto.getStockCode());
		double totalPrice = (double)orderRequestDto.getNumberOfStocks()*price;
		if(account.getBalance() < totalPrice)
			throw new InsufficientBalanceException(INSUFFICIENT_BALANCE.getErrorMessage());
		Optional<PortfolioDetail> portfolioDetail = portfolioDetailRepository.findByInvestementAccount(account);
		PortfolioDetail detail = null;
		if(portfolioDetail.isPresent())
			detail = portfolioDetail.get();
		OrderDetail orderDetail = OrderDetail.builder()
				.quantity(orderRequestDto.getNumberOfStocks())
				.stockPrice(price)
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