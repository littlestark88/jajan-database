package com.littlestark.jajan.controller.error;

import com.littlestark.jajan.model.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.AuthenticationException;

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

    @ExceptionHandler(ForbiddenException.class)
    BaseResponse<Object> forbidden(ForbiddenException forbiddenException) {
        return BaseResponse.builder()
                .code(400)
                .status("Forbidden")
                .message("forbiddenException.getMessage()")
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    BaseResponse<Object> authentication(AuthenticationException authenticationException) {
        return BaseResponse.builder()
                .code(403)
                .status("Forbidden")
                .message("Salah")
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(
                        BaseResponse.<String>builder()
                                .code(exception.getStatusCode().value())
                                .status("Failed")
                        .message(exception.getReason())
                        .build());
    }

}
