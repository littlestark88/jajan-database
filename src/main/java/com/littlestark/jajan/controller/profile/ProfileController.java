package com.littlestark.jajan.controller.profile;

import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.profile.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/profile")
public class ProfileController {

    private IProfileService profileService;

    @PutMapping(
            value = "/{userId}/verification"
    )
    public BaseResponse<Object> updateProfile(
            @RequestParam("value") Boolean verificationProfile,
            @PathVariable("userId") String userId
            ) {
        BaseResponse<Object> profile = profileService.verificationProfile(
                userId,
                verificationProfile);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("Berhasil verification profile")
                .build();
    }

    @PostMapping("/get/{userId}/image-verification")
    public BaseResponse<Object> uploadVerificationProfile(
            @RequestParam("value") MultipartFile file,
            @PathVariable("userId") String userId
            ) throws IOException {
        BaseResponse<Object> profile = profileService.uploadImageVerificationProfile(
                userId,
                file);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("Berhasil posting gambar profile")
                .build();
    }

    @GetMapping(value = "/image")
    public BaseResponse<Object> getImageVerification() throws IOException {
        BaseResponse<Object> profile = profileService.getAllImageVerificationProfile();
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .data(profile.getData())
                .build();
    }
}
