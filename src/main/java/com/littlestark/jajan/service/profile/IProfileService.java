package com.littlestark.jajan.service.profile;

import com.littlestark.jajan.model.request.user.ProfileUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;


public interface IProfileService {
    BaseResponse<Object> updateProfile(String userId, ProfileUserRequest profileUserRequest);
}
