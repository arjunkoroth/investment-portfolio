package com.hackathon.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "http://STOCK-SERVICE/stock-service")
public interface StockServiceProxy {
    
    @GetMapping
    public double getStockPrice(String stockCode);

}
