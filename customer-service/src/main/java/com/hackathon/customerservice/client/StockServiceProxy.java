package com.hackathon.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "http://STOCK-SERVICE/stock-service")
public interface StockServiceProxy {

}
