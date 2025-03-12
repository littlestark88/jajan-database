package com.littlestark.jajan.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String userId;
    private boolean isVerificationUser;
    private String email;
    private String phoneNumber;
}
