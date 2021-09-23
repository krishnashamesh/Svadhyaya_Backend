package com.svadhyaya.backend.models.data;

public class ErrorResponseData {
    private final String errorMessage;

    public ErrorResponseData(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
