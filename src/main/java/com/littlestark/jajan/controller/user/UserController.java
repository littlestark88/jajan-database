package com.littlestark.jajan.controller.user;

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
            @RequestParam("value") String password
    ) {
        BaseResponse<Object> user = userService.changePassword(userId,password);
        return BaseResponse.builder()
                .code(user.getCode())
                .isSuccess(true)
                .message("Berhasil ganti password")
                .build();
    }

    @PostMapping(
            value = "/{userId}/change-phone-number",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> changePhoneNumber(
            @PathVariable("userId") String userId,
            @RequestParam("value") String phoneNumber
    ) {
        BaseResponse<Object> user = userService.changePhoneNumber(userId,phoneNumber);
        return BaseResponse.builder()
                .code(user.getCode())
                .isSuccess(true)
                .message("Berhasil ganti no hp")
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

    @GetMapping(
            value = "/{userId}/get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getUser(
            @PathVariable("userId") String userId
    ) {
        BaseResponse<Object> user = userService.getUserById(userId);
        return BaseResponse.builder()
                .code(user.getCode())
                .isSuccess(true)
                .message("Berhasil mendapatkan data user")
                .data(user.getData())
                .build();
    }

    @PostMapping(
            value = "/{userId}/image-verification",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> uploadImageVerificationUser(
            @PathVariable String userId,
            @RequestParam("value") MultipartFile imageVerificationUser) throws IOException {
        BaseResponse<Object> verificationUser = userService.uploadImageVerificationUser(userId, imageVerificationUser);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(verificationUser.getMessage())
                .data(null)
                .build();
    }

    @GetMapping(
            value = "/image-verification/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFiles(
            @PathVariable String id
    ) {
        var filesDB = userService.getVerificationUserImageById(id);

        return ResponseEntity.ok()
                .body(filesDB.getImageData());

    }
}
