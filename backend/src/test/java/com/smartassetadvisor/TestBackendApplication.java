package com.smartassetadvisor;



import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes = SmartInvestmentAdvisorApplication.class)  // ✅ Correct
public class TestBackendApplication {

    @Test
    void contextLoads() {
    }
}
