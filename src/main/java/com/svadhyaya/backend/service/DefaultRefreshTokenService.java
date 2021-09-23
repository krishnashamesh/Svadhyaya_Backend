package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.RefreshTokenModel;
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

    public Optional<RefreshTokenModel> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenModel createRefreshToken(String username) {
        RefreshTokenModel refreshToken = new RefreshTokenModel();

        refreshToken.setUser(userRepository.findByUsername(username));
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtUtil.getRefreshExpiryDate()));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.saveAndFlush(refreshToken);
        return refreshToken;
    }

    public RefreshTokenModel verifyExpiration(RefreshTokenModel token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }
}
