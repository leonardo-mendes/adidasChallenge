package com.adidas.bff.graphql.input;

import com.adidas.bff.model.EmailSubscription;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EmailSubscriptionInput implements Serializable {

    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean consentSubscribe;

    public EmailSubscription buildDomain() {
        return EmailSubscription.builder()
                .firstName(this.firstName)
                .email(this.email)
                .dateOfBirth(this.dateOfBirth)
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
