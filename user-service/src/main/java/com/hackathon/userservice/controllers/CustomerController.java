package com.hackathon.userservice.controllers;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.userservice.dto.AccountDTO;
import com.hackathon.userservice.dto.PortfolioDetails;
import com.hackathon.userservice.exceptions.FieldErrorDto;
import com.hackathon.userservice.service.AccountService;
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
	
	
	@ApiOperation(value = "Get list of Account.", response = Book.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfull operation", response = AccountDTO.class),
					@ApiResponse(code = 404, message = "Account Not Found", response = FieldErrorDto.class) })
	@GetMapping("/accounts")
	public List<AccountDTO> getCustomerAccounts(){
		log.info("");
		return accountService.getAccounts("");
	}
	
	@GetMapping("/{accountNumber}/portfolio")
	public List<PortfolioDetails> getPortfolioDetails(@PathVariable("accountNumber") @NotNull String accountNumber){
		return null;
	}

}
