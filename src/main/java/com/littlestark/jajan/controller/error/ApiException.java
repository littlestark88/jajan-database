package com.littlestark.jajan.controller.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timesStamp;

    public ApiException(
            String message,
            Throwable throwable,
            HttpStatus httpStatus,
            ZonedDateTime timesStamp) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timesStamp = timesStamp;
    }
}
