package com.adidas.bff.config.client.mock;

import com.adidas.bff.integration.SubscriptionIntegrationClient;
import com.adidas.bff.integration.request.SubscriptionRequest;
import com.adidas.bff.integration.response.SubscriptionResponse;

import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.lang.System.currentTimeMillis;

public class SubscriptionIntegrationClientMock implements SubscriptionIntegrationClient {

    @Override
    public List<SubscriptionResponse> findAll() {
        return List.of(buildResponse());
    }

    @Override
    public SubscriptionResponse findById(Integer id) {
        return buildResponse();
    }

    @Override
    public SubscriptionResponse insert(SubscriptionRequest request) {
        return buildResponse();
    }

    @Override
    public void delete(Integer id) {
    }

    private SubscriptionResponse buildResponse() {
        return SubscriptionResponse.builder()
                .id(123)
                .email("test@test.com")
                .firstName("Test")
                .gender("male")
                .dateOfBirth(currentTimeMillis())
                .consentSubscribe(TRUE)
                .build();
    }
}
