package com.moon.project_two.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

// Use Apache HttpClient 5
// Enable pooling
// Configure max connections
// Add retry / circuit breaker (Resilience4j)

@Configuration
public class RestTemplateConfig {
    
    @Value("${http.client.max-total-connections:100}")
    private int maxTotalConnections;

    @Value("${http.client.max-connections-per-route:20}")
    private int maxPerRoute;

    @Value("${http.client.connect-timeout:5}")
    private int connectTimeout;

    @Value("${http.client.response-timeout:10}")
    private int responseTimeout;

    @Value("${http.client.connection-request-timeout:3}")
    private int connectionRequestTimeout;

    // Step 2: Connection Pool
    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager manager = 
            new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(maxTotalConnections);
        manager.setDefaultMaxPerRoute(maxPerRoute);
        return manager;
    }

    // Step 3: Timeouts
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(connectTimeout))
            .setResponseTimeout(Timeout.ofSeconds(responseTimeout))
            .setConnectionRequestTimeout(
                Timeout.ofSeconds(connectionRequestTimeout))
            .build();
    }

    // Step 4: HttpClient Engine
    @Bean
    public CloseableHttpClient httpClient(
            PoolingHttpClientConnectionManager connectionManager,
            RequestConfig requestConfig) {
        return HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)
            .evictExpiredConnections()
            .evictIdleConnections(TimeValue.ofSeconds(30))
            .build();
    }

    // Step 5: RestTemplate with HttpClient plugged in
    @Bean
    public RestTemplate restTemplate(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory =
            new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
