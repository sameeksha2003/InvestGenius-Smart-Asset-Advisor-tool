package com.smartassetadvisor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Service
public class MarketMoodService {

    @Value("${marketmood.api.key}")
    private String marketMoodApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getMarketSentiment() {
        String url = "https://marketmoodapi.com/sentiment?apikey=" + marketMoodApiKey;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK ? response.getBody() : "Error fetching market mood";
        } catch (Exception e) {
            return "Market Mood API failed: " + e.getMessage();
        }
    }
}
