package com.littlestark.jajan.controller.auth;

import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.auth.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/auth")
public class AuthenticationController {

    private IAuthenticationService userService;

    @PostMapping(
            value = "/register",
            produces = "application/json",
            consumes = "application/json"
    )
    public BaseResponse<Object> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        BaseResponse<Object> user = userService.createUser(createUserRequest);
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
            produces = "application/json",
            consumes = "application/json"
    )
    public BaseResponse<Object> authenticationUser(@RequestBody LoginUserRequest loginUserRequest){
        BaseResponse<Object> user = userService.authenticationLogin(loginUserRequest);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .token(user.getToken())
                .message(user.getMessage())
                .data(null)
                .build();
    }
}
