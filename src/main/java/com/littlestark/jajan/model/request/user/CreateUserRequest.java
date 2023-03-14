package com.littlestark.jajan.model.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @NotNull
    @NotBlank(
            message = "Email tidak boleh kosong, mohon untuk memasukan email anda"
    )
    @Email(
            message = "Tolong masukan email anda dengan format yang benar"
    )
    private String email;

    @NotBlank(
            message = "Password tidak boleh kosong, mohon untuk memasukan password anda"
    )
    @NotNull
    String password;
}
