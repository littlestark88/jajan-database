package com.littlestark.jajan.controller.error;

import com.littlestark.jajan.model.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = ConstraintViolationException.class)
    BaseResponse<Object> validationHandler(ConstraintViolationException constraintViolationException) {
        return BaseResponse.builder()
                .code(400)
                .status("Bad Request")
                .message(constraintViolationException.getMessage())
                .build();
    }

    @ExceptionHandler(value = NotFoundException.class)
    BaseResponse<Object> notFound(NotFoundException notFoundException) {
        return BaseResponse.builder()
                .code(400)
                .status("Not Found")
                .message(notFoundException.getMessage())
                .build();
    }

}
