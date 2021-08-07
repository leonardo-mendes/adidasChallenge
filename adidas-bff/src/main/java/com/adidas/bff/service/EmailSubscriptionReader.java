package com.adidas.bff.service;

import com.adidas.bff.model.EmailSubscription;

import java.util.List;

public interface EmailSubscriptionReader {

    EmailSubscription findById(Integer id);

    List<EmailSubscription> findAll();
}
