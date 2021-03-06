package com.svadhyaya.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    private ConfigUtil configUtil;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(this.configUtil.getProperty("jwt.secret.key")).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                        + Integer.valueOf(this.configUtil.getProperty("jwt.secret.expire.timeinms"))))
                .signWith(SignatureAlgorithm.HS256, this.configUtil.getProperty("jwt.secret.key")).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Integer getRefreshExpiryDate() {
        return Integer.valueOf(this.configUtil.getProperty("jwt.refresh.expire.timeinms"));
    }

    public String getUserNamefromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            userName = extractUsername(authorizationHeader.substring(7));
        }
        return userName;
    }

    public String getJwtTokenfromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
        }
        return jwtToken;
    }
}