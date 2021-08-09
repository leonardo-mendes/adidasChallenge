package com.adidas.subscription.resource.request;

import static java.lang.System.currentTimeMillis;

import com.adidas.subscription.domain.Subscription;
import com.adidas.subscription.resource.validation.GenderValue;
import javax.validation.constraints.Email;
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

    @Email(message = "0000000000003")
    @NotBlank(message = "0000000000002")
    private String email;

    private String firstName;

    @GenderValue(message = "0000000000004")
    private String gender;

    @NotNull(message = "0000000000002")
    private Long dateOfBirth;

    @NotNull(message = "0000000000002")
    private Boolean consentSubscribe;

    public Subscription buildDomain() {
        return Subscription.builder()
                .email(this.email)
                .firstName(this.firstName)
                .gender(this.gender.toLowerCase())
                .dateOfBirth(this.dateOfBirth)
                .createdAt(currentTimeMillis())
                .consentSubscribe(this.consentSubscribe)
                .build();
    }
}
