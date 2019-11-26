package com.org.home.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsZipCodeValidator implements ConstraintValidator<UsZipCode, String> {
    @Override
    public void initialize(UsZipCode phoneNumber) {
    }

    @Override
    public boolean isValid(String usZipCode, ConstraintValidatorContext cxt) {
        return usZipCode != null && usZipCode.matches("^[0-9]{5}(?:-[0-9]{4})?$")
                && (usZipCode.length() == 5);
    }
}