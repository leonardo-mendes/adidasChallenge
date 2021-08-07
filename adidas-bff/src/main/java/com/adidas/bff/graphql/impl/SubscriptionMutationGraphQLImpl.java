package com.adidas.bff.graphql.impl;

import com.adidas.bff.graphql.SubscriptionMutationGraphQL;
import com.adidas.bff.graphql.input.EmailSubscriptionInput;
import com.adidas.bff.model.EmailSubscription;
import com.adidas.bff.service.EmailSubscriptionWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionMutationGraphQLImpl implements SubscriptionMutationGraphQL {

    private final EmailSubscriptionWriter subscriptionWriter;

    @Override
    public EmailSubscription subscribe(EmailSubscriptionInput input) {
        return subscriptionWriter.create(input.buildDomain());
    }

    @Override
    public Boolean unsubscribe(Integer id) {
        return subscriptionWriter.delete(id);
    }
}
