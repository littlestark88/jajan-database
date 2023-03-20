package com.littlestark.jajan.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {

    private Integer code;
    private String status;
    private String message;
    private String token;
    private T data;
}
