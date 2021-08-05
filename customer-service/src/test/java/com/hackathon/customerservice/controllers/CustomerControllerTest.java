package com.hackathon.customerservice.controllers;


import com.hackathon.customerservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController userController;
    @Mock
    private UserServiceImpl userService;


    @Test
    public void testLogin(){

    }


}
