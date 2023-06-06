package com.littlestark.jajan.service.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class ValidationUtils implements IValidationService {

    @Autowired
    private Validator validator;


    @Override
    public void validate(Object request) {
        Set<ConstraintViolation<Object>> validate = validator.validate(request);
        if(validate.size() != 0) {
            throw new ConstraintViolationException(validate);
        }
    }
}
