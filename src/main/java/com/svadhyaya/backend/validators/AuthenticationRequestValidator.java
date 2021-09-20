package com.svadhyaya.backend.validators;

import com.svadhyaya.backend.db.models.User;
import com.svadhyaya.backend.models.AuthenticationRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateAuthenticationRequestValidator")
public class AuthenticationRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AuthenticationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) target;
        if (checkInputString(authenticationRequest.getUserName())) {
            errors.rejectValue("userName", "userName.empty");
        }
        if (!checkInputString(authenticationRequest.getUserName()) && authenticationRequest.getUserName().length() < 6) {
            errors.rejectValue("userName", "userName.min.error");
        }

        if (checkInputString(authenticationRequest.getPassword())) {
            errors.rejectValue("password", "password.empty");
        }
        if (!checkInputString(authenticationRequest.getPassword()) && authenticationRequest.getPassword().length() < 6) {
            errors.rejectValue("password", "password.min.error");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}

