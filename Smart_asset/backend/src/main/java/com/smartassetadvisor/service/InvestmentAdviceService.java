package com.smartassetadvisor.service;

import com.smartassetadvisor.model.User;
import com.smartassetadvisor.model.MarketMood;
import com.smartassetadvisor.repository.UserRepository;
import com.smartassetadvisor.repository.MarketMoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentAdviceService {

    private final UserRepository userRepository;
    private final MarketMoodRepository marketMoodRepository;

    public InvestmentAdviceService(UserRepository userRepository, MarketMoodRepository marketMoodRepository) {
        this.userRepository = userRepository;
        this.marketMoodRepository = marketMoodRepository;
    }

    public String getInvestmentAdvice(Long userId) {
        Optional<User> userProfileOpt = userRepository.findById(userId);
        if (userProfileOpt.isEmpty()) {
            return "User profile not found.";
        }

        User user = userProfileOpt.get();
        String riskProfile = user.getRiskCategory();
        List<MarketMood> marketMoodList = marketMoodRepository.findAll();

        String marketStatus = analyzeMarketMood(marketMoodList);
        return generateAdvice(riskProfile, marketStatus);
    }

    private String analyzeMarketMood(List<MarketMood> marketMoodList) {
        long openMarkets = marketMoodList.stream().filter(m -> m.getCurrentStatus().equalsIgnoreCase("open")).count();
        return (openMarkets > marketMoodList.size() / 2) ? "Bullish" : "Bearish";
    }

    private String generateAdvice(String riskProfile, String marketStatus) {
        switch (riskProfile) {
            case "High":
                return marketStatus.equals("Bullish") ?
                        "📈 Invest in high-growth stocks and aggressive ETFs." :
                        "⚠️ Reduce equity exposure. Hold cash & wait for better opportunities.";
            case "Medium":
                return marketStatus.equals("Bullish") ?
                        "📊 Balanced portfolio: 60% stocks, 30% bonds, 10% alternatives." :
                        "📉 Increase bond exposure. Reduce stock allocation.";
            case "Low":
                return marketStatus.equals("Bullish") ?
                        "🔵 Focus on blue-chip stocks and index funds." :
                        "🛑 Stay in fixed deposits, bonds, and cash equivalents.";
            default:
                return "🚀 Diversify across asset classes for risk protection.";
        }
    }
}
