package com.adidas.bff.config;

import com.adidas.bff.graphql.input.EmailSubscriptionInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;

public class BaseData {

    @Autowired
    protected GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    protected ObjectMapper mapper;

    protected EmailSubscriptionInput SUBSCRIPTION_INPUT =
            EmailSubscriptionInput.builder()
                    .firstName("test")
                    .consentSubscribe(TRUE)
                    .gender("male")
                    .dateOfBirth(LocalDate.now())
                    .email("test@teste.com")
                    .build();
}
