package com.sallatiy.discountService.costumeAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TrimmedStringValidator.class)
public @interface TrimmedString {
    String message() default "Value must not have leading or trailing spaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
