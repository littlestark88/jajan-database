package com.littlestark.jajan.controller.user;

import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.request.user.ChangePhoneNumberRequest;
import com.littlestark.jajan.model.request.user.ResendEmailVerificationRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/jajan/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PutMapping(
            value = "/{userId}/change-password",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> changePassword(
            @PathVariable("userId") String userId,
            @RequestBody ChangePasswordRequest changePasswordRequest
            ) {
        BaseResponse<Object> user = userService.changePassword(userId,changePasswordRequest);
        return BaseResponse.builder()
                .message(user.getMessage())
                .isSuccess(user.isSuccess())
                .build();
    }

    @PostMapping(
            value = "/{userId}/change-phone-number",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> changePhoneNumber(
            @PathVariable("userId") String userId,
            @RequestBody ChangePhoneNumberRequest changePhoneNumberRequest
            ) {
        BaseResponse<Object> user = userService.changePhoneNumber(userId,changePhoneNumberRequest);
        return BaseResponse.builder()
                .message(user.getMessage())
                .isSuccess(user.isSuccess())
                .build();
    }

    @PutMapping(
            value = "/{userId}/verification",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> verificationUser(
            @PathVariable("userId") String userId,
            @RequestParam("value") Boolean isVerificationUser
    ) {
        BaseResponse<Object> user = userService.verificationUser(userId,isVerificationUser);
        return BaseResponse.builder()
                .message(user.getMessage())
                .isSuccess(user.isSuccess())
                .build();
    }


    @PostMapping(
            value = "/resend-email-verification",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> resendEmailVerification(
            @RequestBody ResendEmailVerificationRequest resendEmailVerificationRequest
            ) {
        BaseResponse<Object> emailResend = userService.resendEmailVerification(resendEmailVerificationRequest.getEmail(), resendEmailVerificationRequest.getToken());
        return BaseResponse.builder()
                .message(emailResend.getMessage())
                .isSuccess(emailResend.isSuccess())
                .build();
    }
}
