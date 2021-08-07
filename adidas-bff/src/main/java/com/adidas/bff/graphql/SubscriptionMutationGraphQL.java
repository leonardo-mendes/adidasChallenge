package com.adidas.bff.graphql;

import com.adidas.bff.graphql.input.EmailSubscriptionInput;
import com.adidas.bff.model.EmailSubscription;
import graphql.kickstart.tools.GraphQLMutationResolver;

public interface SubscriptionMutationGraphQL extends GraphQLMutationResolver {

    EmailSubscription subscribe(EmailSubscriptionInput input);

    Boolean unsubscribe(Integer id);

}
