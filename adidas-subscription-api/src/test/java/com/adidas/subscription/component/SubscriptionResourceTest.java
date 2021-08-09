package com.adidas.subscription.component;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.adidas.subscription.component.config.FeignTestConfiguration;
import com.adidas.subscription.repository.SubscriptionRepository;
import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.resource.response.SubscriptionResponse;
import com.adidas.subscription.util.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(FeignTestConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionResourceTest extends MockData {

    private static final String SUBSCRIPTION_URL = "/subscriptions";

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper mapper;

    @Autowired private SubscriptionRepository repository;

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void should_create_a_executed_subscription() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        mockMvc.perform(
                        post(SUBSCRIPTION_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(buildSubscriptionRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(request.getFirstName())))
                .andExpect(jsonPath("$.email", is(request.getEmail())))
                .andExpect(jsonPath("$.gender", is(request.getGender())))
                .andExpect(jsonPath("$.dateOfBirth", notNullValue()))
                .andExpect(jsonPath("$.consentSubscribe", is(request.getConsentSubscribe())))
                .andExpect(jsonPath("$.executed", is(TRUE)))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }

    @Test
    void should_create_a_not_executed_subscription() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setEmail("failed@test.com");
        mockMvc.perform(
                        post(SUBSCRIPTION_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(request.getFirstName())))
                .andExpect(jsonPath("$.email", is(request.getEmail())))
                .andExpect(jsonPath("$.gender", is(request.getGender())))
                .andExpect(jsonPath("$.dateOfBirth", is(request.getDateOfBirth())))
                .andExpect(jsonPath("$.consentSubscribe", is(request.getConsentSubscribe())))
                .andExpect(jsonPath("$.executed", is(FALSE)))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }

    @Test
    void should_not_create_subscription_with_invalid_email() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setEmail("failed");
        mockMvc.perform(
                        post(SUBSCRIPTION_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000003")))
                .andExpect(jsonPath("$.errors.[0].message", is("Invalid email format.")));
    }

    @Test
    void should_not_create_subscription_with_invalid_gender() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setGender("test");
        mockMvc.perform(
                        post(SUBSCRIPTION_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000004")))
                .andExpect(jsonPath("$.errors.[0].message", is("Invalid gender value.")));
    }

    @Test
    void should_not_create_subscription_with_not_null_property() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setConsentSubscribe(null);
        mockMvc.perform(
                        post(SUBSCRIPTION_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000002")))
                .andExpect(
                        jsonPath(
                                "$.errors.[0].message",
                                matchesPattern("The property .* can not be null/blank.")));
    }

    @Test
    void should_delete_subscription_by_id() throws Exception {
        SubscriptionResponse createdSubscription = createSubscription();

        mockMvc.perform(
                        delete(format("%s/%s", SUBSCRIPTION_URL, createdSubscription.getId()))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_subscription_by_id() throws Exception {
        mockMvc.perform(
                        delete(format("%s/%s", SUBSCRIPTION_URL, 9))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000001")))
                .andExpect(
                        jsonPath(
                                "$.errors.[0].message",
                                matchesPattern("The entity with id .* was not found.")));
    }

    @Test
    void should_retrieve_subscription_by_id() throws Exception {
        SubscriptionResponse createdSubscription = createSubscription();

        mockMvc.perform(
                        get(format("%s/%s", SUBSCRIPTION_URL, createdSubscription.getId()))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdSubscription.getId())))
                .andExpect(jsonPath("$.firstName", is(createdSubscription.getFirstName())))
                .andExpect(jsonPath("$.email", is(createdSubscription.getEmail())))
                .andExpect(jsonPath("$.gender", is(createdSubscription.getGender())))
                .andExpect(jsonPath("$.dateOfBirth", is(createdSubscription.getDateOfBirth())))
                .andExpect(
                        jsonPath(
                                "$.consentSubscribe",
                                is(createdSubscription.getConsentSubscribe())))
                .andExpect(jsonPath("$.executed", is(createdSubscription.getExecuted())))
                .andExpect(jsonPath("$.createdAt", is(createdSubscription.getCreatedAt())));
    }

    @Test
    void should_not_retrieve_subscription_by_id() throws Exception {
        mockMvc.perform(
                        get(format("%s/%s", SUBSCRIPTION_URL, 99))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000001")))
                .andExpect(
                        jsonPath(
                                "$.errors.[0].message",
                                matchesPattern("The entity with id .* was not found.")));
    }

    @Test
    void should_retrieve_all_subscriptions() throws Exception {
        SubscriptionResponse createdSubscription = createSubscription();

        mockMvc.perform(get(format(SUBSCRIPTION_URL)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(createdSubscription.getId())))
                .andExpect(jsonPath("$.[0].firstName", is(createdSubscription.getFirstName())))
                .andExpect(jsonPath("$.[0].email", is(createdSubscription.getEmail())))
                .andExpect(jsonPath("$.[0].gender", is(createdSubscription.getGender())))
                .andExpect(jsonPath("$.[0].dateOfBirth", is(createdSubscription.getDateOfBirth())))
                .andExpect(
                        jsonPath(
                                "$.[0].consentSubscribe",
                                is(createdSubscription.getConsentSubscribe())))
                .andExpect(jsonPath("$.[0].executed", is(createdSubscription.getExecuted())))
                .andExpect(jsonPath("$.[0].createdAt", is(createdSubscription.getCreatedAt())));
    }

    private SubscriptionResponse createSubscription() throws Exception {
        String response =
                mockMvc.perform(
                                post(SUBSCRIPTION_URL)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(
                                                mapper.writeValueAsString(
                                                        buildSubscriptionRequest())))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        return mapper.readValue(response, SubscriptionResponse.class);
    }
}
