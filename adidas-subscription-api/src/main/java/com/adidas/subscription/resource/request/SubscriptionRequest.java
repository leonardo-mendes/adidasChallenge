package com.adidas.subscription.resource.request;

import static java.lang.System.currentTimeMillis;

import com.adidas.subscription.domain.Subscription;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "0000000000002")
    private String email;

    private String firstName;

    private String gender;

    @NotBlank(message = "0000000000002")
    private String dateOfBirth;

    @NotNull(message = "0000000000002")
    private Boolean consentSubscribe;

    public Subscription buildDomain() {
        return Subscription.builder()
                .email(this.email)
                .firstName(this.firstName)
                .gender(this.gender)
                .dateOfBirth(this.dateOfBirth)
                .createdAt(currentTimeMillis())
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
