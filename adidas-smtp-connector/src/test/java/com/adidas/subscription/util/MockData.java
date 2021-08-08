package com.adidas.subscription.util;

import com.adidas.subscription.resource.request.SubscriptionRequest;

public class MockData {

    protected SubscriptionRequest buildSubscriptionRequest() {
        return SubscriptionRequest.builder()
                .email("test@test.com")
                .firstName("test")
                .gender("male")
                .dateOfBirth("0000-00-00 00:00:00")
                .consentSubscribe(Boolean.TRUE)
                .build();
    }
}
