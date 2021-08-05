package com.hackathon.customerservice.controllers;

import java.awt.print.Book;
import java.util.List;

import com.hackathon.customerservice.dto.OrderRequestDto;
import com.hackathon.customerservice.dto.OrderResponseDto;
import com.hackathon.customerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.dto.PortfolioDetails;
import com.hackathon.customerservice.exceptions.FieldErrorDto;
import com.hackathon.customerservice.service.AccountService;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	
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

	@PostMapping("/{accountNumber}/orders")
	public ResponseEntity<OrderResponseDto> orderStock(@RequestBody OrderRequestDto requestDto, 
													   @PathVariable String accountNumber){
		log.info("ordering stock "+ requestDto.getStockCode());
		return new ResponseEntity<>(userService.orderstock(requestDto,accountNumber),OK);
	}

}
