package com.adidas.subscription.integration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.adidas.subscription.integration.request.SubscriptionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Profile("!test")
@FeignClient(url = "${smtp.connector.api.url}/send-email", name = "smtp-connector-api")
public interface SmtpConnectorIntegrationClient {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    Boolean sendEmail(@RequestBody SubscriptionRequest request);
}
