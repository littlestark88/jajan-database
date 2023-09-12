package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.entity.VerificationUserEntity;
import com.littlestark.jajan.model.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {

    BaseResponse<Object> changePassword(String userId, String password);
    BaseResponse<Object> changePhoneNumber(String userId, String phoneNumber);

    BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser);

    BaseResponse<Object> getUserById(String userId);
    BaseResponse<Object> uploadImageVerificationUser(
            String userId,
            MultipartFile imageUser
    ) throws IOException;

    VerificationUserEntity getVerificationUserImageById(String id);
}
