package com.sallatiy.discountService.costumeAnnotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimmedStringValidator implements ConstraintValidator<TrimmedString, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        String trimmedValue = s.trim();
        return trimmedValue.equals(s);
    }
}
