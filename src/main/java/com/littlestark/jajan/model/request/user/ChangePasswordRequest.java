package com.littlestark.jajan.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotNull
    @NotBlank(
            message = "Email tidak boleh kosong, mohon untuk memasukan username anda"
    )
    private String email;

    @NotBlank(
            message = "Password tidak boleh kosong, mohon untuk memasukan password anda"
    )
    @NotNull
    private String password;
}
