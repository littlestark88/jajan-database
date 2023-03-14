package com.littlestark.jajan.controller.error;

import com.littlestark.jajan.model.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = ConstraintViolationException.class)
    BaseResponse<String> validationHandler(ConstraintViolationException constraintViolationException) {
        BaseResponse<String> webResponse = new BaseResponse<>();
        webResponse.setCode(400);
        webResponse.setStatus("Bad Request");
        webResponse.setMessage(constraintViolationException.getMessage());
        return webResponse;
    }

    @ExceptionHandler(value = NotFoundException.class)
    BaseResponse<String> notFound(NotFoundException notFoundException) {
        BaseResponse<String> webResponse = new BaseResponse<>();
        webResponse.setCode(400);
        webResponse.setStatus("Not Found");
        webResponse.setData("Not Found");
        return webResponse;
    }

    @ExceptionHandler(value = ApiRequestException.class)
    BaseResponse<String> handleApiRequest(ApiRequestException notFoundException) {
        BaseResponse<String> webResponse = new BaseResponse<>();
        webResponse.setCode(400);
        webResponse.setStatus("Not Found");
        webResponse.setData(notFoundException.getMessage());
        return webResponse;
    }

}
