package com.littlestark.jajan.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotNull
    @NotBlank(
            message = "Password tidak boleh kosong, mohon untuk memasukan password anda"
    )
    private String oldPassword;

    @NotBlank(
            message = "Password tidak boleh kosong, mohon untuk memasukan password anda"
    )
    @NotNull
    private String newPassword;
}
