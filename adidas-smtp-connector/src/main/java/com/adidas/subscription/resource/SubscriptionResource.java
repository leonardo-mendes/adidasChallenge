package com.adidas.subscription.resource;

import static org.springframework.http.HttpStatus.OK;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.service.SubscriptionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/send-email")
@RequiredArgsConstructor
public class SubscriptionResource {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(code = OK)
    Boolean sendEmail(@Valid @RequestBody SubscriptionRequest request) {
        return subscriptionService.sendEmail(request);
    }
}
