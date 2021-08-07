package com.adidas.bff.integration.response;

import com.adidas.bff.model.EmailSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

import static java.time.Instant.ofEpochMilli;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponse {

    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean consentSubscribe;

    public EmailSubscription of() {
        return EmailSubscription.builder()
                .id(this.id)
                .firstName(this.firstName)
                .gender(this.gender)
                .email(this.email)
                .dateOfBirth(this.dateOfBirth)
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
