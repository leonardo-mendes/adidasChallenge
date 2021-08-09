package com.adidas.bff.model;

import com.adidas.bff.integration.request.SubscriptionRequest;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class EmailSubscription implements Serializable {

    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private LocalDate dateOfBirth;
    private Boolean consentSubscribe;

    public SubscriptionRequest buildRequest() {
        return SubscriptionRequest.builder()
                .firstName(this.firstName)
                .email(this.email)
                .gender(this.gender)
                .dateOfBirth(this.dateOfBirth.toEpochDay())
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
