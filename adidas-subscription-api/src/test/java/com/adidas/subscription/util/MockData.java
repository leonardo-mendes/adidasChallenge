package com.adidas.subscription.util;

import static java.lang.Boolean.TRUE;
import static java.lang.System.currentTimeMillis;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.resource.request.SubscriptionRequest;

public class MockData {

    protected Subscription buildSubscription() {
        return Subscription.builder()
                .id(123)
                .email("test@test.com")
                .firstName("test")
                .gender("male")
                .dateOfBirth(System.currentTimeMillis())
                .consentSubscribe(TRUE)
                .createdAt(currentTimeMillis())
                .build();
    }

    protected SubscriptionRequest buildSubscriptionRequest() {
        return SubscriptionRequest.builder()
                .email("test@test.com")
                .firstName("test")
                .gender("male")
                .dateOfBirth(System.currentTimeMillis())
                .consentSubscribe(TRUE)
                .build();
    }
}
