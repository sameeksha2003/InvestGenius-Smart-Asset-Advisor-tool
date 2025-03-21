package com.smartassetadvisor.controller;

import com.smartassetadvisor.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/investment")
@RequiredArgsConstructor
public class InvestmentController {
    
    private final InvestmentService investmentService;

    @GetMapping("/advice")
    public ResponseEntity<String> getInvestmentAdvice(@RequestParam String email) {
        return ResponseEntity.ok(investmentService.getInvestmentAdvice(email));
    }
}
