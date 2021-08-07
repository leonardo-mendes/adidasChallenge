package com.adidas.bff.service;

import com.adidas.bff.model.EmailSubscription;

public interface EmailSubscriptionWriter {

    EmailSubscription create(EmailSubscription emailSubscription);

    Boolean delete (Integer id);
}
