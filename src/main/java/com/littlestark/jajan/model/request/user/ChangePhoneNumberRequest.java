package com.littlestark.jajan.model.request.user;

import lombok.Data;

@Data
public class ChangePhoneNumberRequest {
    private String oldPhoneNumber;
    private String newPhoneNumber;
}
