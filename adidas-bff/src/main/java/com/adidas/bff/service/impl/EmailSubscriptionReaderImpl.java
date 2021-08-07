package com.adidas.bff.service.impl;

import com.adidas.bff.integration.SubscriptionIntegrationClient;
import com.adidas.bff.integration.response.SubscriptionResponse;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailSubscriptionReaderImpl implements EmailSubscriptionReader {

    private final SubscriptionIntegrationClient integrationClient;

    @Override
    public EmailSubscription findById(Integer id) {
        return integrationClient.findById(id).of();
    }

    @Override
    public List<EmailSubscription> findAll() {
        return integrationClient.findAll().parallelStream().map(SubscriptionResponse::of).collect(Collectors.toList());
    }
}
