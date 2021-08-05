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
import com.hackathon.customerservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
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

    @Test
    public void testOrderStock() {
        when(investmentAccountRepository.findByAccountNumber(any(String.class)))
                .thenReturn(Optional.of(new InvestmentAccount()));
        when(stockServiceProxy.getStrockPrice(any()))
                .thenReturn(StockDTO.builder().build());
        when(portfolioDetailRepository.findByInvestementAccount(any(InvestmentAccount.class)))
                .thenReturn(Optional.of(new PortfolioDetail()));
        OrderResponseDto response = userService.orderstock(OrderRequestDto.builder().build(),"accountNumber");
        assertNotNull(response);
        assertEquals(response.getStatusCode(),201);

    }

}
