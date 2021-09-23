package com.svadhyaya.backend.validators;

import com.svadhyaya.backend.db.models.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserModel user = (UserModel) target;
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

