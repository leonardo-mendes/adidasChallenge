package com.adidas.bff.error;

import com.adidas.bff.error.exception.FeignGraphQLException;
import com.adidas.bff.error.model.ErrorResponse;
import com.adidas.bff.error.model.GraphQlError;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionBuilder {

    private final ObjectMapper mapper;

    public FeignGraphQLException buildFeignException(FeignException feignException) {
        List<GraphQlError> errors = extractResponseBody(feignException);
        if (errors.isEmpty()) return new FeignGraphQLException(feignException.getMessage());
        return new FeignGraphQLException(errors.toString());
    }

    private List<GraphQlError> extractResponseBody(FeignException feignException) {
        if (feignException.responseBody().isPresent()) {
            try {
                ByteBuffer byteBuffer = feignException.responseBody().orElseThrow();
                ErrorResponse errorBody = mapper.readValue(byteBuffer.array(), ErrorResponse.class);
                return errorBody.getErrors()
                        .parallelStream()
                        .map(apiError -> GraphQlError.builder()
                                .property(apiError.getArgs()[0])
                                .message(apiError.getMessage())
                                .build())
                        .collect(Collectors.toList());
            } catch (IOException exception) {
                log.error("Cannot read response body from FeignException");
            }
        }
        return List.of();
    }
}
