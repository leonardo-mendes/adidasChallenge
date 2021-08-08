package com.adidas.subscription.unit;

import static java.lang.Boolean.TRUE;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.error.exceptions.NotFoundException;
import com.adidas.subscription.integration.SmtpConnectorIntegrationClient;
import com.adidas.subscription.repository.SubscriptionRepository;
import com.adidas.subscription.resource.response.SubscriptionResponse;
import com.adidas.subscription.service.impl.SubscriptionServiceImpl;
import com.adidas.subscription.util.MockData;
import feign.FeignException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest extends MockData {

    @Mock SubscriptionRepository subscriptionRepository;

    @Mock SmtpConnectorIntegrationClient integrationClient;

    @InjectMocks SubscriptionServiceImpl subscriptionService;

    @Test
    void should_create_subscription() {
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(buildSubscription());
        when(integrationClient.sendEmail(any())).thenReturn(TRUE);
        SubscriptionResponse response = subscriptionService.create(buildSubscriptionRequest());
        assertNotNull(response);
        verify(subscriptionRepository).save(any(Subscription.class));
        verify(integrationClient).sendEmail(any());
    }

    @Test
    void should_create_subscription_and_not_execute_subscription() {
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(buildSubscription());
        when(integrationClient.sendEmail(any())).thenThrow(FeignException.class);
        assertDoesNotThrow(() -> subscriptionService.create(buildSubscriptionRequest()));
        verify(subscriptionRepository).save(any(Subscription.class));
        verify(integrationClient).sendEmail(any());
    }

    @Test
    void should_delete_subscription() {
        when(subscriptionRepository.findById(anyInt())).thenReturn(of(buildSubscription()));
        doNothing().when(subscriptionRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> subscriptionService.delete(1));
        verify(subscriptionRepository).deleteById(anyInt());
    }

    @Test
    void should_not_delete_subscription_with_invalid_id() {
        when(subscriptionRepository.findById(anyInt())).thenReturn(empty());
        assertThrows(NotFoundException.class, () -> subscriptionService.delete(1));
        verify(subscriptionRepository).findById(anyInt());
        verify(subscriptionRepository, times(0)).deleteById(anyInt());
    }

    @Test
    void should_retrieve_subscription_by_id() {
        when(subscriptionRepository.findById(anyInt())).thenReturn(of(buildSubscription()));
        SubscriptionResponse response = subscriptionService.findById(1);
        assertNotNull(response);
        verify(subscriptionRepository).findById(anyInt());
    }

    @Test
    void should_not_retrieve_subscription_by_id() {
        when(subscriptionRepository.findById(anyInt())).thenReturn(empty());
        assertThrows(NotFoundException.class, () -> subscriptionService.findById(1));
        verify(subscriptionRepository).findById(anyInt());
    }

    @Test
    void should_retrieve_all_subscriptions() {
        when(subscriptionRepository.findAll()).thenReturn(List.of(buildSubscription()));
        List<SubscriptionResponse> response = subscriptionService.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(subscriptionRepository).findAll();
    }
}
