package com.littlestark.jajan.model.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    @NotNull
    @NotBlank(
            message = "Email tidak boleh kosong, mohon untuk memasukan username anda"
    )
    @Email(
            message = "Tolong masukan email anda dengan format yang benar"
    )
    private String email;

    @NotBlank(
            message = "Password tidak boleh kosong, mohon untuk memasukan password anda"
    )
    @NotNull
    private String password;
}
