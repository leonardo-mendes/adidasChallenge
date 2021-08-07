package com.adidas.bff.integration;

import com.adidas.bff.integration.request.SubscriptionRequest;
import com.adidas.bff.integration.response.SubscriptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Profile("!test")
@FeignClient(url = "${subscription.api.url}/subscriptions", name = "subscription-api")
public interface SubscriptionIntegrationClient {

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    List<SubscriptionResponse> findAll();

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    SubscriptionResponse findById(@PathVariable(value = "id") final Integer id);


    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    SubscriptionResponse insert(@RequestBody SubscriptionRequest request);

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable(value = "id") final Integer id);

}
