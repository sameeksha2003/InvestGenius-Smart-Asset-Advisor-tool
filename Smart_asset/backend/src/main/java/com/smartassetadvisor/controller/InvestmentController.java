package com.smartassetadvisor.controller;

import com.smartassetadvisor.service.InvestmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping("/analyze")
    public String analyzeInvestment() {
        investmentService.analyzeInvestment();
        return "Investment analysis completed. Check logs for results.";
    }
}
