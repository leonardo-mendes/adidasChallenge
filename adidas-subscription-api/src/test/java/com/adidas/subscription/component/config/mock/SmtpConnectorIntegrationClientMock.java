package com.adidas.subscription.component.config.mock;

import com.adidas.subscription.integration.SmtpConnectorIntegrationClient;
import com.adidas.subscription.integration.request.SubscriptionRequest;

public class SmtpConnectorIntegrationClientMock implements SmtpConnectorIntegrationClient {

    @Override
    public Boolean sendEmail(SubscriptionRequest request) {
        return request.getEmail().contains("failed") ? Boolean.FALSE : Boolean.TRUE;
    }
}
