package com.adidas.bff.config.client;

import com.adidas.bff.config.client.mock.SubscriptionIntegrationClientMock;
import com.adidas.bff.integration.SubscriptionIntegrationClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class FeignTestConfiguration {

    @Bean
    @Primary
    public SubscriptionIntegrationClient subscriptionConfigIntegrationClient() {
        return new SubscriptionIntegrationClientMock();
    }

}
