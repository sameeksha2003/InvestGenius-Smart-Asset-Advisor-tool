package com.smartassetadvisor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String name;
    private String email;
    private String password;
    private int age;
    private String occupation;
    private String annualIncome;
    private String riskCategory;
    private String investmentGoals;
}
