package com.svadhyaya.backend.advice;

import com.svadhyaya.backend.exception.TokenRefreshException;
import com.svadhyaya.backend.models.data.ErrorResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class TokenControllerAdvice {
    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseData handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new ErrorResponseData(ex.getMessage());
    }
}
