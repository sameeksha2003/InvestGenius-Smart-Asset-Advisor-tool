package com.smartassetadvisor.service;

import com.smartassetadvisor.model.User;
import com.smartassetadvisor.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    public void analyzeInvestment() {
        System.out.println("Investment analysis completed.");
    }

    public InvestmentService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public String getInvestmentRecommendations(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = userOptional.get();
        String riskCategory = user.getRiskCategory();

        return recommendInvestments(riskCategory);
    }

    private String recommendInvestments(String riskCategory) {
        switch (riskCategory.toLowerCase()) {
            case "low":
                return "Recommended: Bonds, Fixed Deposits, Gold.";
            case "medium":
                return "Recommended: ETFs, Large Cap Stocks.";
            case "high":
                return "Recommended: Small Caps, Crypto, Options Trading.";
            default:
                return "Risk category unknown. Please update your profile.";
        }
    }

    public String getStockMarketData(String symbol) {
        String apiKey = "YOUR_ALPHA_VANTAGE_API_KEY";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=" + apiKey;
        
        return restTemplate.getForObject(url, String.class);
    }
}
