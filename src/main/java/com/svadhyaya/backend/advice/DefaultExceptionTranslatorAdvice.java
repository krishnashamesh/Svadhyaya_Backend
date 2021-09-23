package com.svadhyaya.backend.advice;

import com.svadhyaya.backend.models.data.ErrorResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionTranslatorAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseData handleRuntimeException(RuntimeException ex) {
        return new ErrorResponseData(ex.getMessage());
    }
}
