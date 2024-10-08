package com.littlestark.jajan.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {

    private String message;
    private String token;
    private boolean isSuccess;
    private T data;
}
