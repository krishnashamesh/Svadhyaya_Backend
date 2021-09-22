package com.svadhyaya.backend.models;

import java.util.List;

public class AuthenticationResponse {

    private final String jwtToken;

    private final String refreshToken;

    public List<String> getRoles() {
        return roles;
    }

    private final List<String> roles;

    public AuthenticationResponse(String jwtToken, String refreshToken, List<String> roles) {
        this.jwtToken = jwtToken;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
