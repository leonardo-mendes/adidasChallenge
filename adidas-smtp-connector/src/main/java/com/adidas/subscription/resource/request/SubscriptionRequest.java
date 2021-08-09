package com.adidas.subscription.resource.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequest {

    @Email(message = "0000000000002")
    private String email;

    private String firstName;

    private String gender;

    @NotNull(message = "0000000000001")
    private Long dateOfBirth;

    @NotNull(message = "0000000000001")
    private Boolean consentSubscribe;
}
