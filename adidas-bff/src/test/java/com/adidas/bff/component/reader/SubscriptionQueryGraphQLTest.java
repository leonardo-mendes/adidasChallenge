package com.adidas.bff.component.reader;

import com.adidas.bff.AdidasBffApplication;
import com.adidas.bff.config.BaseData;
import com.adidas.bff.config.client.FeignTestConfiguration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Import(FeignTestConfiguration.class)
@SpringBootTest(
        classes = {AdidasBffApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionQueryGraphQLTest extends BaseData {

    @Test
    void should_retrieve_subscription_by_id() throws Exception {
        final ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("id", mapper.convertValue(1, JsonNode.class));
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/subscription.graphql", rootNode);

        assertNotNull(response);
        assertTrue(response.isOk());
        assertTrue(response.getRawResponse().getBody().contains("123"));
    }

    @Test
    void should_retrieve_all_subscriptions() throws Exception {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/subscriptions.graphql");

        assertNotNull(response);
        assertTrue(response.isOk());
        assertTrue(response.getRawResponse().getBody().contains("123"));
    }
}
