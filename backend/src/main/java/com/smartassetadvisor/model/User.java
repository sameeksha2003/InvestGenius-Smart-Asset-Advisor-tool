package com.smartassetadvisor.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails { // ✅ Implements UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String occupation;
    private Double annualIncome;
    private String riskCategory;
    private String investmentGoals;

    // ✅ Default constructor
    public User() {}

    // ✅ Constructor
    public User(String name, String email, String password, Integer age, String occupation, Double annualIncome, String riskCategory, String investmentGoals) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.occupation = occupation;
        this.annualIncome = annualIncome;
        this.riskCategory = riskCategory;
        this.investmentGoals = investmentGoals;
    }

    // ✅ Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No roles for now
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { // Spring Security uses "username" instead of "email"
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public Double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(Double annualIncome) { this.annualIncome = annualIncome; }
    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }
    public String getInvestmentGoals() { return investmentGoals; }
    public void setInvestmentGoals(String investmentGoals) { this.investmentGoals = investmentGoals; }
}
