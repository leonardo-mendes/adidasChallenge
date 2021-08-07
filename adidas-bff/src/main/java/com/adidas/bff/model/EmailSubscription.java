package com.adidas.bff.model;

import com.adidas.bff.integration.request.SubscriptionRequest;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.System.currentTimeMillis;

@Data
@Builder
public class EmailSubscription implements Serializable {

    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean consentSubscribe;

    public SubscriptionRequest buildRequest() {
        return SubscriptionRequest.builder()
                .firstName(this.firstName)
                .email(this.email)
                .dateOfBirth(this.dateOfBirth)
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
