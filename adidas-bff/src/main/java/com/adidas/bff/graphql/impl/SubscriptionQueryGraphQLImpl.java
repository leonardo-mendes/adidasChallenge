package com.adidas.bff.graphql.impl;

import com.adidas.bff.graphql.SubscriptionQueryGraphQL;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionQueryGraphQLImpl implements SubscriptionQueryGraphQL {

    private final EmailSubscriptionReader subscriptionReader;

    @Override
    public List<EmailSubscription> subscriptions() {
        return subscriptionReader.findAll();
    }

    @Override
    public EmailSubscription subscription(Integer id) {
        return subscriptionReader.findById(id);
    }
}
