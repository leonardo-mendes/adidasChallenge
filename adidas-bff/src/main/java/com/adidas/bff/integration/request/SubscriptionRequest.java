package com.adidas.bff.integration.request;

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
}
