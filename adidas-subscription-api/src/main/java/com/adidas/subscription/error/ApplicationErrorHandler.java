package com.adidas.subscription.error;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import com.adidas.subscription.error.exceptions.NotFoundException;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationErrorHandler {

    public static final String LOG_EXCEPTION = "Exception= {}, Stack Trace= {}";

    private final ApplicationErrorMessage applicationErrorMessage;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            final MethodArgumentNotValidException exception, final Locale locale) {
        ResponseEntity<ErrorResponse> error =
                badRequest()
                        .body(
                                applicationErrorMessage.getErrorMessage(
                                        locale,
                                        BAD_REQUEST.value(),
                                        exception.getBindingResult().getFieldErrors()));
        log.error(LOG_EXCEPTION, error, exception);
        return error;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            final NotFoundException exception, final Locale locale) {
        ResponseEntity<ErrorResponse> error =
                status(NOT_FOUND)
                        .body(
                                applicationErrorMessage.getErrorMessage(
                                        locale,
                                        NOT_FOUND.value(),
                                        exception.getMessageKey(),
                                        exception.getNotFoundValue()));
        log.error(LOG_EXCEPTION, error, exception);
        return error;
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(
            final Locale locale, final String messageKey, final ServerWebInputException exception) {
        ResponseEntity<ErrorResponse> error =
                status(BAD_REQUEST)
                        .body(
                                applicationErrorMessage.getErrorMessage(
                                        locale, BAD_REQUEST.value(), messageKey));
        log.error(LOG_EXCEPTION, error, exception);
        return error;
    }
}
