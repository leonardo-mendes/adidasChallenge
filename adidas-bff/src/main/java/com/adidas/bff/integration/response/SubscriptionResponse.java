package com.adidas.bff.integration.response;

import com.adidas.bff.model.EmailSubscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.Instant.ofEpochMilli;
import static java.time.ZoneId.systemDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponse {

    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private Long dateOfBirth;
    private Boolean consentSubscribe;

    public EmailSubscription of() {
        return EmailSubscription.builder()
                .id(this.id)
                .firstName(this.firstName)
                .gender(this.gender)
                .email(this.email)
                .dateOfBirth(ofEpochMilli(this.dateOfBirth).atZone(systemDefault()).toLocalDate())
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
