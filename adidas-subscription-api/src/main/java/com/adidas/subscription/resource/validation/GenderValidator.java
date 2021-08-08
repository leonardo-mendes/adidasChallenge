package com.adidas.subscription.resource.validation;

import static java.lang.Boolean.TRUE;
import static java.util.Optional.ofNullable;

import com.adidas.subscription.resource.util.Gender;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderValue, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ofNullable(value).isPresent()) {
            return Arrays.stream(Gender.values())
                    .anyMatch(genderValue -> genderValue.name().equalsIgnoreCase(value));
        }
        return TRUE;
    }
}
