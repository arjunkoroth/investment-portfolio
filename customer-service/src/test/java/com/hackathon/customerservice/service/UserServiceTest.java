package com.hackathon.customerservice.service;

import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.entity.InvestmentAccount;
import com.hackathon.customerservice.entity.UserDetail;
import com.hackathon.customerservice.entity.UserRole;
import com.hackathon.customerservice.exceptions.NoInvestmentAccountFoundException;
import com.hackathon.customerservice.repository.InvestmentAccountRepository;
import com.hackathon.customerservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
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
    private InvestmentAccountRepository investmentAccountRepository;


    @Test
    public void test() {

    }


    @Test
    public void testInvestmentAccountsSuccess() {
        List<InvestmentAccount> investmentAccounts = new ArrayList<>();
        investmentAccounts.add(new InvestmentAccount(1, "Monesh123", 8000.0,
                new UserDetail(2,"hackethoncustomer","Monesh",new UserRole(1,"ROLE_ADMIN"))));
        when(userRepository.findByCustomerId(any(String.class)))
                .thenReturn(Optional.of(new UserDetail(2,"hackethoncustomer","Monesh",new UserRole(1,"ROLE_ADMIN"))));
        when(investmentAccountRepository.findAllByUserDetail(any(Optional.class),any(Pageable.class)))
                .thenReturn(investmentAccounts);
        List<AccountDTO> response = userService.getAccounts("hackethoncustomer",0);
       // assertNotNull(response);
        assertEquals("Monesh123",response.get(0).getAccountNumber());
        assertEquals(8000.0,response.get(0).getBalance());
    }

    @Test
    public void testInvestmentAccountsFailure() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());
        assertThrows(NoInvestmentAccountFoundException.class,()-> userService.getAccounts("2", 6));
    }


}
