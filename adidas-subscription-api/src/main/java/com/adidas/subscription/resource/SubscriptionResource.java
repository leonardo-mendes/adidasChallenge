package com.adidas.subscription.resource;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.resource.response.SubscriptionResponse;
import com.adidas.subscription.service.SubscriptionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subscriptions")
@RequiredArgsConstructor
public class SubscriptionResource {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(code = CREATED)
    SubscriptionResponse insert(@Valid @RequestBody SubscriptionRequest request) {
        return subscriptionService.create(request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = NO_CONTENT)
    void delete(@PathVariable(value = "id") final Integer id) {
        subscriptionService.delete(id);
    }

    @GetMapping(path = "/{id}")
    SubscriptionResponse findById(@PathVariable(value = "id") final Integer id) {
        return subscriptionService.findById(id);
    }

    @GetMapping
    List<SubscriptionResponse> findAll() {
        return subscriptionService.findAll();
    }
}
