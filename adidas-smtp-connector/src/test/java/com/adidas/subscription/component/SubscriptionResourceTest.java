package com.adidas.subscription.component;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.util.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionResourceTest extends MockData {

    private static final String SEND_EMAIL_URL = "/send-email";

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper mapper;

    @Test
    void should_send_email() throws Exception {
        mockMvc.perform(
                        post(SEND_EMAIL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(buildSubscriptionRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(TRUE)));
    }

    @Test
    void should_not_send_email() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setEmail("failed@test.com");
        mockMvc.perform(
                        post(SEND_EMAIL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(FALSE)));
    }

    @Test
    void should_not_send_email_with_invalid_email() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setEmail("failed");
        mockMvc.perform(
                        post(SEND_EMAIL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000002")))
                .andExpect(jsonPath("$.errors.[0].message", is("Invalid email format.")));
    }

    @Test
    void should_not_create_subscription_with_not_null_property() throws Exception {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setConsentSubscribe(null);
        mockMvc.perform(
                        post(SEND_EMAIL_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(400)))
                .andExpect(jsonPath("$.errors.[0].code", is("0000000000001")))
                .andExpect(
                        jsonPath(
                                "$.errors.[0].message",
                                matchesPattern("The property .* can not be null/blank.")));
    }
}
