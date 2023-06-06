package com.littlestark.jajan.controller.profile;

import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.ProfileUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.profile.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/profile")
public class ProfileController {

    private IProfileService profileService;

    @PutMapping(
            value = "/{userId}"
    )
    public BaseResponse<Object> updateProfile(
            @RequestBody ProfileUserRequest profileUserRequest,
            @PathVariable("userId") String userId
            ) {
        BaseResponse<Object> profile = profileService.updateProfile(
                userId,
                profileUserRequest);
        return BaseResponse.builder()
                .code(200)
                .isSuccess(true)
                .message("Berhasil memperbarui profile")
                .build();
    }
}
