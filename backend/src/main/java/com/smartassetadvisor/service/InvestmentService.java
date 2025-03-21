package com.smartassetadvisor.service;

import com.smartassetadvisor.model.User;
import com.smartassetadvisor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InvestmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarketMoodService marketMoodService;

    public String getInvestmentAdvice(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        String marketMood = marketMoodService.getMarketSentiment();

        // Determine investment strategy based on user data
        String advice;
        if (user.getRiskCategory().equalsIgnoreCase("High")) {
            advice = "High-risk portfolio: Crypto, Small Caps, Growth Stocks.";
        } else if (user.getRiskCategory().equalsIgnoreCase("Medium")) {
            advice = "Balanced approach: ETFs, Large Caps, Mutual Funds.";
        } else {
            advice = "Low-risk investments: Bonds, Fixed Deposits, Gold.";
        }

        return "Market Mood: " + marketMood + "\nSuggested Investment: " + advice;
    }
}
