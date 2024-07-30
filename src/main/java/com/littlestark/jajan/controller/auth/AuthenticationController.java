package com.littlestark.jajan.controller.auth;

import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.user.LoginResponse;
import com.littlestark.jajan.service.auth.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/auth")
public class AuthenticationController {

    private IAuthenticationService userService;

    @PostMapping(
            value = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        BaseResponse<Object> user = userService.registerUser(createUserRequest);
        return BaseResponse.builder()
                .message(user.getMessage())
                .data(null)
                .isSuccess(user.isSuccess())
                .build();
    }


    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> authenticationUser(@RequestBody LoginUserRequest loginUserRequest) {
        BaseResponse<Object> user = userService.authenticationLogin(loginUserRequest);
        LoginResponse loginResponse = new LoginResponse(user.getData().toString());
        return BaseResponse.builder()
                .token(user.getToken())
                .message(user.getMessage())
                .data(loginResponse)
                .isSuccess(user.isSuccess())
                .build();
    }
}
