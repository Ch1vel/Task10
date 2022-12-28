package com.example.Task10.util;

import com.example.Task10.models.User;
import com.example.Task10.services.RegistrationService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    private final RegistrationService registrationService;

    public UserValidator(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(!registrationService.registration(user)) {
            errors.rejectValue("nickname","","Nickname has already used");
        }
    }
}
