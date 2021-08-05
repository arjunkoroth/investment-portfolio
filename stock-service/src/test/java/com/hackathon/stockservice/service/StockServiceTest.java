package com.hackathon.stockservice.service;


import com.hackathon.stockservice.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StockServiceTest {
    @InjectMocks
    private StockService service;
    @Mock
    private StockRepository tableNameRepository;

    @Test
    public void test(){

    }

}
