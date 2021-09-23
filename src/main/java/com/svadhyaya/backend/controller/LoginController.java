package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.db.models.RefreshTokenModel;
import com.svadhyaya.backend.exception.TokenRefreshException;
import com.svadhyaya.backend.models.data.AuthenticationData;
import com.svadhyaya.backend.models.data.ErrorResponseData;
import com.svadhyaya.backend.models.data.TokenRefreshData;
import com.svadhyaya.backend.models.request.AuthenticationRequest;
import com.svadhyaya.backend.models.request.TokenRefreshRequest;
import com.svadhyaya.backend.service.DefaultRefreshTokenService;
import com.svadhyaya.backend.service.DefaultUserDetailsService;
import com.svadhyaya.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DefaultRefreshTokenService refreshTokenService;

    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException exception) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseData("Incorrect username or Password"));
        } catch (UsernameNotFoundException usernameNotFoundException) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseData("UserModel not found, Please register"));
        } catch (Throwable throwable) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseData("Something went wrong"));
        }
        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseData("UserModel not found, Please register"));
        }
        String jwtToken;
        RefreshTokenModel refreshToken;
        if (Objects.nonNull(userDetails)) {
            jwtToken = jwtUtil.generateToken(userDetails);
            refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseData("Something went wrong"));
        }
        return ResponseEntity.ok(new AuthenticationData(jwtToken, refreshToken.getToken(),
                userDetails.getAuthorities().stream()
                        .map(user -> user.getAuthority()).collect(Collectors.toList())));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenModel::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user.getUsername()));
                    return ResponseEntity.ok(new TokenRefreshData(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}
