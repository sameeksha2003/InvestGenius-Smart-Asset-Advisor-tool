package com.smartassetadvisor.controller;

import com.smartassetadvisor.dto.InvestmentRecommendation;
import com.smartassetadvisor.enums.InvestmentStrategy;
import com.smartassetadvisor.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping("/{riskCategory}")
    public List<InvestmentRecommendation> getInvestmentAdvice(@PathVariable String riskCategory) {
        InvestmentStrategy strategy;
        try {
            strategy = InvestmentStrategy.valueOf(riskCategory.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid risk category: " + riskCategory);
        }
        return investmentService.getInvestmentAdvice(strategy);
    }
}
