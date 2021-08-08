package com.adidas.subscription.resource.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean consentSubscribe;
    private Boolean executed;
    private Long createdAt;
}
