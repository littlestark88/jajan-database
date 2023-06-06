package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.response.BaseResponse;

public interface IUserService {

    BaseResponse<Object> changePassword(String userId, String password);

    BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser);
}
