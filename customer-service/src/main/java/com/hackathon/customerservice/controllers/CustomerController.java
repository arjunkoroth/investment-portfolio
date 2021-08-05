package com.hackathon.customerservice.controllers;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.dto.PortfolioDetails;
import com.hackathon.customerservice.exceptions.FieldErrorDto;
import com.hackathon.customerservice.security.JwtTokenUtil;
import com.hackathon.customerservice.service.AccountService;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HttpServletRequest httpServlet;
	
	
	@ApiOperation(value = "Get list of Account.", response = Book.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfull operation", response = AccountDTO.class),
					@ApiResponse(code = 404, message = "Account Not Found", response = FieldErrorDto.class) })
	@GetMapping("/accounts")
	public List<AccountDTO> getCustomerAccounts(){
		log.info("");
		return accountService.getAccounts("");
	}
	
	@ApiOperation(value = "Get portfolio details of a account.", response = Book.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfull operation", response = AccountDTO.class),
					@ApiResponse(code = 404, message = "Account Not Found", response = FieldErrorDto.class) })
	@GetMapping("/{accountNumber}/portfolio")
	public PortfolioDetails getPortfolioDetails(@PathVariable("accountNumber") @NotNull String accountNumber){
		String token = httpServlet.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
		final String customerId = jwtTokenUtil.getUsername(token);		
		return accountService.getPortfolio(customerId,Optional.of(accountNumber));
	}

}
