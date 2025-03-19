package com.smartassetadvisor.controller;

import com.smartassetadvisor.dto.StockData;
import com.smartassetadvisor.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}")
    public StockData getStockPrice(@PathVariable String symbol) {
        return stockService.getStockPrice(symbol);
    }
}
