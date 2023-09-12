package com.littlestark.jajan.controller.auth;

import com.littlestark.jajan.controller.error.ForbiddenException;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.user.LoginResponse;
import com.littlestark.jajan.service.auth.IAuthenticationService;
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
                .code(200)
                .status("Success")
                .token(user.getToken())
                .message(user.getMessage())
                .data(null)
                .build();
    }


    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> authenticationUser(@RequestBody LoginUserRequest loginUserRequest) throws ForbiddenException {
        BaseResponse<Object> user = userService.authenticationLogin(loginUserRequest);
        LoginResponse loginResponse = new LoginResponse(user.getData().toString());
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .token(user.getToken())
                .message(user.getMessage())
                .isSuccess(user.getIsSuccess())
                .data(loginResponse)
                .build();
    }
}
