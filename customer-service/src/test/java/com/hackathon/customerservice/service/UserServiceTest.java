package com.hackathon.customerservice.service;

import com.hackathon.customerservice.client.StockServiceProxy;
import com.hackathon.customerservice.dto.OrderRequestDto;
import com.hackathon.customerservice.dto.OrderResponseDto;
import com.hackathon.customerservice.dto.StockDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.PortfolioDetail;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.OrderDetailRepository;
import com.hackathon.customerservice.repository.PortfolioDetailRepository;
import com.hackathon.customerservice.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.entity.UserRole;
import com.hackathon.customerservice.exceptions.InvalidCredentialsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
	
	@Mock
    private UserRepository userRepository;
    @Mock
    private InvestmentAccountRepository investmentAccountRepository;
    @Mock
    private PortfolioDetailRepository portfolioDetailRepository;
    @Mock
    private StockServiceProxy stockServiceProxy;
    @Mock
    private OrderDetailRepository orderDetailRepository;
    

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testOrderStock() {
        when(investmentAccountRepository.findByAccountNumber(any(String.class)))
                .thenReturn(Optional.of(new InvestmentAccount()));
        when(stockServiceProxy.getStrockPrice(any()))
                .thenReturn(StockDTO.builder().build());
        when(portfolioDetailRepository.findByInvestementAccount(any(InvestmentAccount.class)))
                .thenReturn(Optional.of(new PortfolioDetail()));
        OrderResponseDto response = userService.orderstock(OrderRequestDto.builder().build(), "accountNumber");
        assertNotNull(response);
        assertEquals(response.getStatusCode(), 201);
    }

   /* @Test
    void testLoginUsingCustomerId() {
    	UserRole userRole = UserRole.builder().id(1).roleName("ROLE_CUSTOMER").build();
    	UserDetail userDetail = UserDetail.builder()
    			.id(1).customerId("mockcustomerid").password("Hackethon@123#").userRole(userRole)
    			.build();
    	List<UserDetail> list = Arrays.asList(userDetail);
    	when(userRepository.findByCustomerId("mockcustomerid")).thenReturn(list);
    	UserDetails expectedUser = userService.loadUserByUsername("mockcustomerid");
    	assertEquals(expectedUser.getUsername(), list.stream().map(UserDetail::getCustomerId).findFirst().get());
    }
    
    @Test
    void testLoginUsingCustomerId_Exception() {
    	List<UserDetail> list = new ArrayList<>();
    	when(userRepository.findByCustomerId("mockcustomerid")).thenReturn(list);
    	assertThrows(InvalidCredentialsException.class, ()-> userService.loadUserByUsername("mockcustomerid"));
    }*/
}
