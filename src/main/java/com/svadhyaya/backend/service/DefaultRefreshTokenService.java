package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.RefreshToken;
import com.svadhyaya.backend.exception.TokenRefreshException;
import com.svadhyaya.backend.repository.RefreshTokenRepository;
import com.svadhyaya.backend.repository.UserRepository;
import com.svadhyaya.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByUsername(username));
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtUtil.getRefreshExpiryDate()));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}
