package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.entity.VerificationUserEntity;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.request.user.ChangePhoneNumberRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.user.UserResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IVerificationUserRepository;
import com.littlestark.jajan.utils.ResourceValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final ResourceValue resourceValue;

    @Override
    public BaseResponse<Object> changePassword(String userId, ChangePasswordRequest changePasswordRequest){

        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            authenticationRepository.save(user);
            message = resourceValue.getSuccessChangePassword();
            isSuccess = true;
        } else {
            message = resourceValue.getChangePasswordFailed();
        }
        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> changePhoneNumber(String userId, ChangePhoneNumberRequest changePhoneNumberRequest) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));

        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(user.getPhoneNumber().equals(changePhoneNumberRequest.getOldPhoneNumber())) {
            user.setPhoneNumber(changePhoneNumberRequest.getNewPhoneNumber());
            authenticationRepository.save(user);
            message = resourceValue.getSuccessChangePhoneNumber();
            isSuccess = true;
        } else {
            message = resourceValue.getPhoneNumberNotSame();
        }
        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(!user.getIsVerificationUser()) {
            user.setIsVerificationUser(isVerificationUser);
            authenticationRepository.save(user);
            message = resourceValue.getSuccessVerificationUser();
            isSuccess = true;
        } else {
            message = resourceValue.getUserVerify();
        }

        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

}
