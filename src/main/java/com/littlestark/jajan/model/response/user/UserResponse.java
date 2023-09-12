package com.littlestark.jajan.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String nameStore;
    private String email;
    private String phoneNumber;
    private Boolean isStoreVerification;
    private Boolean isUserVerification;
}
