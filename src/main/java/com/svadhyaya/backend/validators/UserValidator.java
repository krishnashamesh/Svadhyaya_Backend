package com.svadhyaya.backend.validators;

import com.svadhyaya.backend.db.models.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (checkInputString(user.getUsername())) {
            errors.rejectValue("name", "name.empty");
        }
        if (!checkInputString(user.getUsername()) && user.getUsername().length() < 6) {
            errors.rejectValue("name", "name.min.error");
        }

        if (checkInputString(user.getPassword())) {
            errors.rejectValue("password", "password.empty");
        }
        if (!checkInputString(user.getPassword()) && user.getPassword().length() < 6) {
            errors.rejectValue("name", "password.min.error");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }

}

