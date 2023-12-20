package com.sw8labs.crypto.fundingfeefarmer.config;

import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UMFuturesClientConfig {

    @Value("${funding-fee-farmer.um-futures-base-url}")
    private String baseUrl;

    @Value("${funding-fee-farmer.api-key}")
    private String apiKey;

    @Value("${funding-fee-farmer.secret-key}")
    private String secretKey;

    @Bean
    public UMFuturesClientImpl umFuturesClient() {
        return new UMFuturesClientImpl(apiKey, secretKey, baseUrl);
    }
}
