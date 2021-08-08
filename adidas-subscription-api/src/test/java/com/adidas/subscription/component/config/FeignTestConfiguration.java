package com.adidas.subscription.component.config;

import com.adidas.subscription.component.config.mock.SmtpConnectorIntegrationClientMock;
import com.adidas.subscription.integration.SmtpConnectorIntegrationClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class FeignTestConfiguration {

    @Bean
    @Primary
    public SmtpConnectorIntegrationClient smtpConnectorIntegrationClient() {
        return new SmtpConnectorIntegrationClientMock();
    }
}
