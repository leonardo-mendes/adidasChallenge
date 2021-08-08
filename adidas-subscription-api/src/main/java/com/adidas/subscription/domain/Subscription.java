package com.adidas.subscription.domain;

import com.adidas.subscription.resource.response.SubscriptionResponse;
import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "consent_subscribe")
    private Boolean consentSubscribe;

    private Boolean executed;

    @Column(name = "created_at")
    private Long createdAt;

    public SubscriptionResponse buildResponse() {
        return SubscriptionResponse.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .gender(this.gender)
                .dateOfBirth(this.dateOfBirth)
                .consentSubscribe(this.consentSubscribe)
                .executed(this.executed)
                .createdAt(this.createdAt)
                .build();
    }
}
