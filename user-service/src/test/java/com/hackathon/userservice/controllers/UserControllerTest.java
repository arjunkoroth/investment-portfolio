package com.hackathon.userservice.controllers;


import com.hackathon.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;


    @Test
    public void testLogin(){

    }


}
