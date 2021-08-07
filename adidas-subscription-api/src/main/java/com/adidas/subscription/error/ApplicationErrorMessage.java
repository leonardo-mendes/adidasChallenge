package com.adidas.subscription.error;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationErrorMessage {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    private static final String DEFAULT_ERROR_MESSAGE =
            "Couldn't find any message for {} code under {} locale";

    private final MessageSource applicationErrorMessageSource;

    public ErrorResponse getErrorMessage(
            Locale locale, int httpStatusCode, String messageKey, String... args) {
        return toErrorResponse(
                httpStatusCode, Collections.singletonList(toApiError(messageKey, locale, args)));
    }

    public ErrorResponse getErrorMessage(
            Locale locale, int httpStatusCode, List<FieldError> allErrors) {
        return toErrorResponse(
                httpStatusCode,
                allErrors
                        .stream()
                        .map(
                                objectError ->
                                        toApiError(
                                                StringUtils.defaultIfBlank(
                                                        objectError.getDefaultMessage(), ""),
                                                locale,
                                                StringUtils.defaultIfBlank(
                                                        objectError.getField(), "")))
                        .collect(Collectors.toList()));
    }

    private ErrorResponse toErrorResponse(int httpStatusCode, List<ApiError> apiErrors) {
        return ErrorResponse.builder().statusCode(httpStatusCode).errors(apiErrors).build();
    }

    private ApiError toApiError(String messageKey, Locale locale, String... args) {
        String message;
        try {
            message = applicationErrorMessageSource.getMessage(messageKey, args, locale);
        } catch (NoSuchMessageException e) {
            log.error(DEFAULT_ERROR_MESSAGE, messageKey, locale);
            message = NO_MESSAGE_AVAILABLE;
        }
        return new ApiError(messageKey, message, args);
    }
}
