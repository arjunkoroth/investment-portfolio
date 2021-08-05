package com.hackathon.customerservice.controllers;


import com.hackathon.customerservice.dto.AccountDTO;
import com.hackathon.customerservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController userController;
    @Mock
    private UserService userService;


    @Test
    public void testLogin(){

    }

    @Test
    public void testGetFavorites(){
        when(userService.getAccounts(any(String.class),any(Integer.class)))
                .thenReturn(buildFavoriteList());
        List<AccountDTO> response = userController.getCustomerAccounts(Optional.of(0));
        assertNotNull(response);
        assertEquals(response.get(0).getAccountNumber(),"number");
    }

    private List<AccountDTO> buildFavoriteList() {
        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(AccountDTO.builder().accountNumber("number").build());
        return  accounts;
    }


}
