package com.littlestark.jajan.controller.user;

import com.littlestark.jajan.controller.error.ApiRequestException;
import com.littlestark.jajan.controller.error.NotFoundException;
import com.littlestark.jajan.model.request.user.ChangePasswordRequest;
import com.littlestark.jajan.model.request.user.CreateUserRequest;
import com.littlestark.jajan.model.request.user.LoginUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.UserResponse;
import com.littlestark.jajan.service.user.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/auth")
@Slf4j
public class UserController {

    private IUserService userService;

    @PostMapping(
            value = "/api/jajan/user",
            produces = "application/json",
            consumes = "application/json"
    )
    BaseResponse<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setStatus("Success");
        baseResponse.setMessage("Berhasil membuat user baru");
        return baseResponse;
    }

//    @GetMapping(
//            value = "/api/jajan/login",
//            produces = "application/json"
//    )
//    BaseResponse<UserResponse> loginUser(@RequestBody LoginUserRequest loginUserRequest) throws NotFoundException {
//        UserResponse userResponse = userService.findUsername(loginUserRequest);
//        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
//        if (userResponse.getPassword().equals(loginUserRequest.getPassword())) {
//            baseResponse.setCode(200);
//            baseResponse.setStatus("Success");
//            baseResponse.setMessage("Berhasil login");
//        } else {
//            baseResponse.setCode(201);
//            baseResponse.setStatus("Fail Login");
//            baseResponse.setMessage("Login Gagal");
//        }
//        return baseResponse;
//    }

    @PutMapping(
            value = "/changepassword",
            produces = "application/json",
            consumes = "application/json"
    )
    BaseResponse<UserResponse> putChangePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        userService.putChangePassword(changePasswordRequest);
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setStatus("Success");
        baseResponse.setMessage("Berhasil ganti password");
        return baseResponse;
    }

    @PostMapping(
            value = "/register",
            produces = "application/json",
            consumes = "application/json"
    )
    public BaseResponse<UserResponse> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        UserResponse user = userService.createUser(createUserRequest);
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setStatus("Success");
        baseResponse.setMessage(user.getToken());
        return baseResponse;
    }


    @PostMapping(
            value = "/login",
            produces = "application/json",
            consumes = "application/json"
    )
    public BaseResponse<UserResponse> authenticationUser(@RequestBody LoginUserRequest loginUserRequest){
        UserResponse user = userService.authenticationLogin(loginUserRequest);
        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setCode(200);
        baseResponse.setStatus("Success");
        baseResponse.setMessage(user.getToken());
        return baseResponse;
    }
}
