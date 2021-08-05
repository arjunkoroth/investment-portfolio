package com.hackathon.stockservice.service;


import com.hackathon.stockservice.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class StockServiceImplTest {
    @InjectMocks
    private StockServiceImpl service;
    @Mock
    private StockRepository tableNameRepository;

    @Test
    public void test(){

    }

}
