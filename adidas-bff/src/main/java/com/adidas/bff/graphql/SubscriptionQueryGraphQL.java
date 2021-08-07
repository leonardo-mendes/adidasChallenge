package com.adidas.bff.graphql;

import com.adidas.bff.model.EmailSubscription;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public interface SubscriptionQueryGraphQL extends GraphQLQueryResolver, GraphQLMutationResolver {

    List<EmailSubscription> subscriptions();

    EmailSubscription subscription(Integer id);
}
