package com.org.home.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsZipCodeValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsZipCode {
    String message() default "Invalid US Zip Code";

    Class[] groups() default {};

    Class[] payload() default {};
}