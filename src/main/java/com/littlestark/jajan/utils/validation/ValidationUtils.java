package com.littlestark.jajan.utils.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class ValidationUtils<T> {

    private Validator validator;

    public void validate(T any) {
        Set<ConstraintViolation<T>> result = validator.validate(any);

        if(result.size() != 0) {
            throw new ConstraintViolationException(result);
        }
    }
}
