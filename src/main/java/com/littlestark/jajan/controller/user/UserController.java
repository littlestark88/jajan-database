package com.littlestark.jajan.controller.user;

import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam("value") String password
    ) {
        BaseResponse<Object> user = userService.changePassword(userId,password);
        return BaseResponse.builder()
                .code(user.getCode())
                .isSuccess(true)
                .message("Berhasil ganti password")
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
                .code(user.getCode())
                .isSuccess(true)
                .message("Berhasil verification user")
                .build();
    }
}
