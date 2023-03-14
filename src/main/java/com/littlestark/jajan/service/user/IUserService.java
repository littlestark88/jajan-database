package com.littlestark.jajan.service.user;

import com.littlestark.jajan.controller.error.NotFoundException;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.response.UserResponse;

public interface IUserService {

    UserResponse createUser(CreateUserRequest createUserRequest);
    UserResponse findUsername(LoginUserRequest loginUserRequest) throws NotFoundException;
    UserResponse authenticationLogin(LoginUserRequest loginUserRequest);

    void putChangePassword(ChangePasswordRequest changePasswordRequest) throws Exception;
}
