package org.application.weatherapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateListValidator.class)
public @interface ValidDateList {
    String message() default "Invalid date format in the list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
