package com.littlestark.jajan.controller.error;

import com.littlestark.jajan.model.response.BaseResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
//                .code(400)
//                .status("Bad Request")
                .message(constraintViolationException.getMessage())
                .build();
    }

    @ExceptionHandler(value = NotFoundException.class)
    BaseResponse<Object> notFound(NotFoundException notFoundException) {
        return BaseResponse.builder()
//                .code(400)
//                .status("Not Found")
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(value = ForbiddenException.class)
    BaseResponse<Object> forbidden(ForbiddenException forbiddenException) {
        return BaseResponse.builder()
//                .code(400)
//                .status("Forbidden")
                .message(forbiddenException.getMessage() + " tes")
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    BaseResponse<Object> authentication(AuthenticationException authenticationException) {
        return BaseResponse.builder()
//                .code(403)
//                .status("Forbidden")
                .message("Salah")
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(
                        BaseResponse.<String>builder()
//                                .code(exception.getStatusCode().value())
//                                .status("Failed")
                        .message(exception.getReason())
                        .build());
    }

//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity<BaseResponse<String>> forbiddenException(ForbiddenException exception) {
//        return ResponseEntity
//                .status(222)
//                .body(
//                        BaseResponse.<String>builder()
////                                .code(exception.getStatusCode().value())
////                                .status("Failed")
//                                .message(exception.getMessage())
//                                .build());
//    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    BaseResponse<Object> methodArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        return BaseResponse.builder()
                .message(methodArgumentNotValidException.getMessage() + " tes")
                .build();
    }

    @ExceptionHandler(Exception.class)
    BaseResponse<Object> handleSecurityException(Exception exception) {
        var message = "";

        exception.printStackTrace();
        if(exception instanceof BadCredentialsException) {
            message = "The username or password is incorrect";
        }

        if (exception instanceof AccountStatusException) {
            message = "The account is locked";
        }

        if (exception instanceof AccessDeniedException) {
            message = "You are not authorized to access this resource";
        }

        if (exception instanceof SignatureException) {
            message = "The JWT signature is invalid";
        }

        if (exception instanceof ExpiredJwtException) {
            message = "The JWT token has expired";
        }

        if (exception instanceof NotFoundException) {
            message = "User not found";
        }

//        if (errorDetail == null) {
//            message = "Unknown internal server error.";
//        }
        return BaseResponse.builder()
                .message(message)
                .build();
    }

//    @ExceptionHandler(NotFoundException.class)
//    BaseResponse<Object> notFound(NotFoundException notFoundException) {
//        return BaseResponse.builder()
//                .build();
//    }

}
