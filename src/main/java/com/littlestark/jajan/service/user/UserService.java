package com.littlestark.jajan.service.user;

import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.entity.VerificationUserEntity;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.user.UserResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IVerificationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class UserService implements IUserService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;
    @Autowired
    private IVerificationUserRepository verificationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<Object> changePassword(String userId, String password){

        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        user.setPassword(passwordEncoder.encode(password));
        authenticationRepository.save(user);
        return BaseResponse.builder()
                .build();
    }

    @Override
    public BaseResponse<Object> changePhoneNumber(String userId, String phoneNumber) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        user.setPhoneNumber(phoneNumber);
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

    @Override
    public BaseResponse<Object> getUserById(String userId) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        var userResponse = UserResponse.builder()
                .nameStore(user.getStoreEntity().getNameStore())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isStoreVerification(user.getStoreEntity().isVerificationStore())
                .isUserVerification(user.getIsVerificationUser())
                .build();

        return BaseResponse.builder()
                .data(userResponse)
                .build();
    }

    @Override
    public BaseResponse<Object> uploadImageVerificationUser(String userId, MultipartFile imageUser) throws IOException {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        var verificationImageUser = VerificationUserEntity.builder()
                .imageData(imageUser.getBytes())
                .userVerification(user)
                .build();

        verificationUserRepository.save(verificationImageUser);

        return BaseResponse.builder()
                .message("Berhasil upload image")
                .build();

    }

    @Override
    public VerificationUserEntity getVerificationUserImageById(String id) {
        var user = authenticationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));
        return user.getVerificationUserEntity();
    }
}
