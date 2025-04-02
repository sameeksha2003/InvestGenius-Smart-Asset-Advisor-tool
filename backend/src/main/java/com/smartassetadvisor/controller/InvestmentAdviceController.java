package com.smartassetadvisor.controller;

import com.smartassetadvisor.service.InvestmentAdviceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investment") // Updated to match frontend request URL
public class InvestmentAdviceController {

    private final InvestmentAdviceService investmentAdviceService;

    public InvestmentAdviceController(InvestmentAdviceService investmentAdviceService) {
        this.investmentAdviceService = investmentAdviceService;
    }

    @GetMapping("/advice/{userId}") // Use path variable for userId
    public String getAdvice(@PathVariable Long userId) {
        return investmentAdviceService.getInvestmentAdvice(userId);
    }
}
