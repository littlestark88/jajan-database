package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements IUserService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Override
    public BaseResponse<Object> changePassword(String userId, String password){

        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        user.setPassword(password);
        authenticationRepository.save(user);
        return BaseResponse.builder().build();
    }

    @Override
    public BaseResponse<Object> verificationUser(String userId, Boolean isVerificationUser) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        user.setIsVerificationUser(isVerificationUser);
        authenticationRepository.save(user);

        return BaseResponse.builder().build();
    }
}
