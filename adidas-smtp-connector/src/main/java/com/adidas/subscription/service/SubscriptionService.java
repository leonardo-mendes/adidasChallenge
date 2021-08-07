package com.adidas.subscription.service;

import com.adidas.subscription.resource.request.SubscriptionRequest;

public interface SubscriptionService {

    Boolean sendEmail(SubscriptionRequest request);
}
