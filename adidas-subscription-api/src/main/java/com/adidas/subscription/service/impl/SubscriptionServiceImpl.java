package com.adidas.subscription.service.impl;

import static java.lang.Boolean.FALSE;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.error.exceptions.NotFoundException;
import com.adidas.subscription.integration.SmtpConnectorIntegrationClient;
import com.adidas.subscription.repository.SubscriptionRepository;
import com.adidas.subscription.resource.request.SubscriptionRequest;
import com.adidas.subscription.resource.response.SubscriptionResponse;
import com.adidas.subscription.service.SubscriptionService;
import feign.FeignException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SmtpConnectorIntegrationClient connectorIntegration;

    @Override
    public SubscriptionResponse create(SubscriptionRequest request) {
        Subscription subscription = request.buildDomain();
        subscription.setExecuted(retrieveEmailResult(subscription));
        return subscriptionRepository.save(subscription).buildResponse();
    }

    private Boolean retrieveEmailResult(Subscription subscription) {
        try {
            return connectorIntegration.sendEmail(
                    new com.adidas.subscription.integration.request.SubscriptionRequest(
                            subscription));
        } catch (FeignException ex) {
            return FALSE;
        }
    }

    @Override
    public void delete(Integer id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public SubscriptionResponse findById(Integer id) {
        return subscriptionRepository
                .findById(id)
                .map(Subscription::buildResponse)
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }

    @Override
    public List<SubscriptionResponse> findAll() {
        return subscriptionRepository
                .findAll()
                .parallelStream()
                .map(Subscription::buildResponse)
                .collect(Collectors.toList());
    }
}
