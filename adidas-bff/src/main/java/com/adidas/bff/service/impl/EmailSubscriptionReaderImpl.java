package com.adidas.bff.service.impl;

import com.adidas.bff.error.ExceptionBuilder;
import com.adidas.bff.integration.SubscriptionIntegrationClient;
import com.adidas.bff.integration.response.SubscriptionResponse;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionReader;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailSubscriptionReaderImpl implements EmailSubscriptionReader {

    private final ExceptionBuilder exceptionBuilder;

    private final SubscriptionIntegrationClient integrationClient;

    @Override
    public EmailSubscription findById(Integer id) {
        try {
            return integrationClient.findById(id).of();
        } catch (FeignException ex) {
            throw exceptionBuilder.buildFeignException(ex);
        }
    }

    @Override
    public List<EmailSubscription> findAll() {
        try {
            return integrationClient.findAll().parallelStream().map(SubscriptionResponse::of).collect(Collectors.toList());
        } catch (FeignException ex) {
            throw exceptionBuilder.buildFeignException(ex);
        }
    }
}
