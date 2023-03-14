package com.littlestark.jajan.model.response;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer code;
    private String status;
    private String message;
    private T data;
}
