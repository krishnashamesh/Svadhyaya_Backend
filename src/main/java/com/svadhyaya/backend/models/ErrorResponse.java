package com.svadhyaya.backend.models;

public class ErrorResponse {
    private final String errorMessage;

    public ErrorResponse(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
