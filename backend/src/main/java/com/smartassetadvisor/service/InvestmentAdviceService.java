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

    public String getInvestmentAdvice(String email) {  // ✅ Accepts email instead of userId
        Optional<User> userProfileOpt = userRepository.findByEmail(email);
        if (userProfileOpt.isEmpty()) {
            return "❌ User profile not found. Please complete your profile to get personalized investment advice.";
        }

        User user = userProfileOpt.get();
        String riskProfile = user.getRiskCategory();
        List<MarketMood> marketMoodList = marketMoodRepository.findAll();

        String marketStatus = analyzeMarketMood(marketMoodList);
        return generateDetailedAdvice(riskProfile, marketStatus);
    }

    private String analyzeMarketMood(List<MarketMood> marketMoodList) {
        long openMarkets = marketMoodList.stream().filter(m -> m.getCurrentStatus().equalsIgnoreCase("open")).count();
        return (openMarkets > marketMoodList.size() / 2) ? "Bullish" : "Bearish";
    }

    private String generateDetailedAdvice(String riskProfile, String marketStatus) {
        StringBuilder advice = new StringBuilder();
        advice.append("📊 **Investment Strategy Based on Your Risk Profile & Market Conditions**\n\n");

        switch (riskProfile.toLowerCase()) {
            case "high":
                advice.append("🔥 **High-Risk Investor Advice:**\n")
                      .append(marketStatus.equals("Bullish") 
                          ? "The market is showing **strong growth**. This is a great time to invest in **high-growth stocks, aggressive ETFs, and crypto**.\n\n"
                          : "⚠️ The market is **volatile**. Reduce your exposure to high-risk assets and **hold some cash for better opportunities**.\n\n")
                      .append("💡 **Recommended Investments:** Small Cap Stocks, Crypto, Options Trading, Growth ETFs.\n");
                break;

            case "medium":
                advice.append("📈 **Medium-Risk Investor Advice:**\n")
                      .append(marketStatus.equals("Bullish") 
                          ? "The market is **stable**. You should maintain a **balanced portfolio** with a mix of stocks and bonds.\n\n"
                          : "📉 Market downturn detected. Consider **increasing bond exposure** and shifting some assets to defensive stocks.\n\n")
                      .append("💡 **Recommended Investments:** Large Cap Stocks, ETFs, 60% Stocks, 30% Bonds, 10% Alternative Assets.\n");
                break;

            case "low":
                advice.append("🔵 **Low-Risk Investor Advice:**\n")
                      .append(marketStatus.equals("Bullish") 
                          ? "Even in a bullish market, it's best to **focus on stability** with index funds and blue-chip stocks.\n\n"
                          : "🛑 The market is **uncertain**. Stick to **fixed deposits, bonds, and cash equivalents** for security.\n\n")
                      .append("💡 **Recommended Investments:** Bonds, Fixed Deposits, Gold, Blue-Chip Stocks, Index Funds.\n");
                break;

            default:
                advice.append("🚀 **General Investment Advice:** Diversify across asset classes to **minimize risk and maximize returns**.\n");
        }

        return advice.toString();
    }
}
