package com.littlestark.jajan.service.profile;

import com.littlestark.jajan.model.request.user.ProfileUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;


public interface IProfileService {
    BaseResponse<Object> updateProfile(String userId, ProfileUserRequest profileUserRequest);
    BaseResponse<Object> verificationProfile(String userId, Boolean verificationProfile);
    BaseResponse<Object> uploadImageVerificationProfile(String userId, MultipartFile file);
    BaseResponse<Object> getAllImageVerificationProfile();
}
