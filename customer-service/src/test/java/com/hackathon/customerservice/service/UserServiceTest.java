package com.hackathon.customerservice.service;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private InvestmentAccountRepository dao;


    @Test
    public void test() {

    }

    @Test
    public void testgetInvestmentAccounts() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new UserDetail()));
        when(bookBorrowerRepository.findAllByUserDetail(any(UserDetail.class)))
                .thenReturn(Collections.singletonList(new BookBorrower()));
        BorrowBookResponseDto response = userService.borrowBooks(new BorrowBookRequestDto(), 1L);
        assertNotNull(response);
        assertEquals(response.getStatusCode(),200);
    }

//

}
