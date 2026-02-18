package com.moon.project_two.Config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// Use Apache HttpClient 5
// Enable pooling
// Configure max connections
// Add retry / circuit breaker (Resilience4j)

@Configuration
public class RestTemplateConfig {
    
    @Bean("labReportRestTemplate")
    public RestTemplate labRestTemplate(RestTemplateBuilder builder){
        return builder
               .rootUri("http://localhost:8081/reports")
               .connectTimeout(Duration.ofSeconds(5))
               .readTimeout(Duration.ofSeconds(10))
               .build();
    }
}
