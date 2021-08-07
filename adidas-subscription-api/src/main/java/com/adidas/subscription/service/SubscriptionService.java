package com.adidas.subscription.service;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.resource.response.SubscriptionResponse;
import java.util.List;

public interface SubscriptionService {

    SubscriptionResponse create(SubscriptionRequest request);

    void delete(Integer id);

    SubscriptionResponse findById(Integer id);

    List<SubscriptionResponse> findAll();
}
