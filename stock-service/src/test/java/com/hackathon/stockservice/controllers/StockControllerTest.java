package com.hackathon.stockservice.controllers;

import com.hackathon.stockservice.service.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StockControllerTest {
    @InjectMocks
    private StockController controller;
    @Mock
    private StockServiceImpl service;

    @Test
    public void test(){

    }

}
