package com.gmail.sergiusz.mazan.games.validation;

import com.gmail.sergiusz.mazan.games.model.UserRegistration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPassword, UserRegistration> {

    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRegistration userRegistration, ConstraintValidatorContext constraintValidatorContext) {
        String password = userRegistration.getPassword();
        if(password != null) {
            String confirmPassword = userRegistration.getConfirmPassword();
            return password.equals(confirmPassword);
        }
        else
            return false;
    }
}
