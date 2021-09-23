package com.svadhyaya.backend.models.data;

public class DefaultResponseData {
    private final String message;

    public DefaultResponseData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
