package com.adidas.bff.service.impl;

import com.adidas.bff.error.ExceptionBuilder;
import com.adidas.bff.integration.SubscriptionIntegrationClient;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionWriter;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSubscriptionWriterImpl implements EmailSubscriptionWriter {

    private final SubscriptionIntegrationClient integrationClient;
    private final ExceptionBuilder exceptionBuilder;

    @Override
    public EmailSubscription create(EmailSubscription emailSubscription) {
        try {
            return integrationClient.insert(emailSubscription.buildRequest()).of();
        } catch (FeignException ex) {
            throw exceptionBuilder.buildFeignException(ex);
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            integrationClient.delete(id);
            return Boolean.TRUE;
        } catch (FeignException ex) {
            throw exceptionBuilder.buildFeignException(ex);
        }
    }
}
