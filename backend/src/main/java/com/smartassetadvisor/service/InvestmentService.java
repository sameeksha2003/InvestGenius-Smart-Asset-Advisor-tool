package com.smartassetadvisor.service;

import com.smartassetadvisor.dto.InvestmentRecommendation;
import com.smartassetadvisor.enums.InvestmentStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentService {

    public List<InvestmentRecommendation> getInvestmentAdvice(InvestmentStrategy riskCategory) {
        List<InvestmentRecommendation> recommendations = new ArrayList<>();

        switch (riskCategory) {
            case LOW_RISK:
                recommendations.add(new InvestmentRecommendation("Fixed Deposits", "Safe and stable returns", 40.0));
                recommendations.add(new InvestmentRecommendation("Bonds", "Government or corporate bonds", 30.0));
                recommendations.add(new InvestmentRecommendation("Gold ETFs", "Stable store of value", 30.0));
                break;

            case MEDIUM_RISK:
                recommendations.add(new InvestmentRecommendation("Mutual Funds", "Diversified investment in stocks", 40.0));
                recommendations.add(new InvestmentRecommendation("Large Cap Stocks", "Stable and well-established companies", 35.0));
                recommendations.add(new InvestmentRecommendation("Bonds", "Some stability with moderate risk", 25.0));
                break;

            case HIGH_RISK:
                recommendations.add(new InvestmentRecommendation("Small Cap Stocks", "High growth potential, high risk", 50.0));
                recommendations.add(new InvestmentRecommendation("Cryptocurrency", "High volatility, high reward", 30.0));
                recommendations.add(new InvestmentRecommendation("Options Trading", "Advanced high-risk trading", 20.0));
                break;
        }
        return recommendations;
    }
}
