package com.adidas.subscription.error.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    public static final String MESSAGE_KEY = "0000000000001";

    @Getter private final String messageKey;

    @Getter private final HttpStatus httpStatus;

    @Getter private final String notFoundValue;

    public NotFoundException(String notFoundValue) {
        super(MESSAGE_KEY);
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.messageKey = MESSAGE_KEY;
        this.notFoundValue = notFoundValue;
    }
}
