package com.adidas.subscription.integration.request;

import com.adidas.subscription.domain.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean consentSubscribe;

    public SubscriptionRequest(Subscription subscription) {
        this.email = subscription.getEmail();
        this.firstName = subscription.getFirstName();
        this.gender = subscription.getGender();
        this.dateOfBirth = subscription.getDateOfBirth();
        this.consentSubscribe = subscription.getConsentSubscribe();
    }

}
