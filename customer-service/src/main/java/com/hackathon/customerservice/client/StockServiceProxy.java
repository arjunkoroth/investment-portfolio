package com.hackathon.customerservice.client;

import com.hackathon.customerservice.dto.StockDTO;
import javax.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "http://STOCK-SERVICE/stock-service")
public interface StockServiceProxy {

    @GetMapping("/{stockCode}")
    public StockDTO getStrockPrice(@PathVariable("stockCode") @NotNull Optional<String> stockCode);

}
