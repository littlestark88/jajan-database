package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.request.user.ChangePhoneNumberRequest;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.response.BaseResponse;

public interface IUserService {

    BaseResponse<Object> changePassword(String userId, ChangePasswordRequest changePasswordRequest);
    BaseResponse<Object> changePhoneNumber(String userId, ChangePhoneNumberRequest changePhoneNumberRequest);
    BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser);
    BaseResponse<Object> resendEmailVerification(String email, String token);


}
