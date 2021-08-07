package com.adidas.bff.service.impl;

import com.adidas.bff.integration.SubscriptionIntegrationClient;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSubscriptionWriterImpl implements EmailSubscriptionWriter {

    private final SubscriptionIntegrationClient integrationClient;

    @Override
    public EmailSubscription create(EmailSubscription emailSubscription) {
        return integrationClient.insert(emailSubscription.buildRequest()).of();
    }

    @Override
    public Boolean delete(Integer id) {
        integrationClient.delete(id);
        return Boolean.TRUE;
    }
}
