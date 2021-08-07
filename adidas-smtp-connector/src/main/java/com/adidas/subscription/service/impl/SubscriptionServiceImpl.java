package com.adidas.subscription.service.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public Boolean sendEmail(SubscriptionRequest request) {
        return request.getEmail().contains("failed") ? resultFalse() : resultTrue();
    }

    Boolean resultTrue() {
        log.info("Sent email");
        return TRUE;
    }

    Boolean resultFalse() {
        log.info("Failed email");
        return FALSE;
    }
}
