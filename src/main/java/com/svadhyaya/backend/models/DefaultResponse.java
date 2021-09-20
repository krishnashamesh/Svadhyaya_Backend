package com.svadhyaya.backend.models;

public class DefaultResponse {
    private final String message;

    public DefaultResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
