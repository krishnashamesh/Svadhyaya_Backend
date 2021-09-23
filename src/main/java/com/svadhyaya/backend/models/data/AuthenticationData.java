package com.svadhyaya.backend.models.data;

import java.util.List;

public class AuthenticationData {

    private final String jwtToken;

    private final String refreshToken;

    public List<String> getRoles() {
        return roles;
    }

    private final List<String> roles;

    public AuthenticationData(String jwtToken, String refreshToken, List<String> roles) {
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
