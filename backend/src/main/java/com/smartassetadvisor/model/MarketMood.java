package com.smartassetadvisor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "market_mood")
public class MarketMood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double moodIndex;  
    private LocalDateTime date = LocalDateTime.now();
}
