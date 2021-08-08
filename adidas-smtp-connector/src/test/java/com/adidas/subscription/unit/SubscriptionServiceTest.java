package com.adidas.subscription.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.service.impl.SubscriptionServiceImpl;
import com.adidas.subscription.util.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest extends MockData {

    @InjectMocks SubscriptionServiceImpl subscriptionService;

    @Test
    void should_send_email() {
        assertTrue(subscriptionService.sendEmail(buildSubscriptionRequest()));
    }

    @Test
    void should_not_send_email() {
        SubscriptionRequest request = buildSubscriptionRequest();
        request.setEmail("failed@test.com");
        assertFalse(subscriptionService.sendEmail(request));
    }
}
