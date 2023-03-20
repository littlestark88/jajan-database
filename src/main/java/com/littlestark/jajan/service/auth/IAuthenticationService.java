package com.littlestark.jajan.service.auth;

import com.littlestark.jajan.controller.error.NotFoundException;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.UserResponse;

public interface IAuthenticationService {

    BaseResponse<Object> createUser(CreateUserRequest createUserRequest);
    BaseResponse<Object> authenticationLogin(LoginUserRequest loginUserRequest);

    void putChangePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
}
