package com.littlestark.jajan.service.auth;

import com.littlestark.jajan.controller.error.ForbiddenException;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;

public interface IAuthenticationService {

    BaseResponse<Object> registerUser(CreateUserRequest createUserRequest);
    BaseResponse<Object> authenticationLogin(LoginUserRequest loginUserRequest) throws ForbiddenException;

}
