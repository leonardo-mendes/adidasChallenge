package com.adidas.bff.graphql.input;

import com.adidas.bff.model.EmailSubscription;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class EmailSubscriptionInput implements Serializable {

    private String email;
    private String firstName;
    private String gender;
    private LocalDate dateOfBirth;
    private Boolean consentSubscribe;

    public EmailSubscription buildDomain() {
        return EmailSubscription.builder()
                .firstName(this.firstName)
                .gender(this.gender)
                .email(this.email)
                .dateOfBirth(this.dateOfBirth)
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
