package com.littlestark.jajan.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime dateRegister;
    private String token;
}
