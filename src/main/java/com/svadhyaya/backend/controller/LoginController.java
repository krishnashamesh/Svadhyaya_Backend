package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.models.AuthenticationRequest;
import com.svadhyaya.backend.models.AuthenticationResponse;
import com.svadhyaya.backend.models.DefaultResponse;
import com.svadhyaya.backend.models.ErrorResponse;
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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException exception) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Incorrect username or Password"));
        } catch (UsernameNotFoundException usernameNotFoundException) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("User not found, Please register"));
        } catch (Throwable throwable) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Something went wrong"));
        }
        UserDetails userDetails = null;

        try {
            userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("User not found, Please register"));
        }
        String jwtToken = "";
        if (Objects.nonNull(userDetails)) {
            jwtToken = jwtUtil.generateToken(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Something went wrong"));
        }
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

}
