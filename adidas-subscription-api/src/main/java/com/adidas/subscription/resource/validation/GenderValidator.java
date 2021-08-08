package com.adidas.subscription.resource.validation;

import com.adidas.subscription.resource.util.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

import static java.lang.Boolean.TRUE;
import static java.util.Optional.ofNullable;

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
