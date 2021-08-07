package com.adidas.bff.component.writer;

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
import org.springframework.context.annotation.Profile;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Import(FeignTestConfiguration.class)
@SpringBootTest(
        classes = {AdidasBffApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscriptionMutationGraphQLTest extends BaseData {

    @Test
    void should_subscribe_user() throws Exception {
        final ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set("subscription", mapper.convertValue(SUBSCRIPTION_INPUT, JsonNode.class));

        GraphQLResponse response =
                graphQLTestTemplate.perform("graphql/subscribe.graphql", objectNode);

        assertNotNull(response);
        assertTrue(response.isOk());
        assertTrue(response.getRawResponse().getBody().contains("123"));
    }

    @Test
    void should_unsubscribe_user() throws Exception {
        final ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set("id", mapper.convertValue(1, JsonNode.class));

        GraphQLResponse response =
                graphQLTestTemplate.perform("graphql/unsubscribe.graphql", objectNode);

        assertNotNull(response);
        assertTrue(response.isOk());
        assertTrue(response.getRawResponse().getBody().contains("true"));
    }
}
