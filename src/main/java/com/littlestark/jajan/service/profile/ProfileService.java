package com.littlestark.jajan.service.profile;

import com.littlestark.jajan.model.entity.ProfileEntity;
import com.littlestark.jajan.model.entity.VerificationProfileEntity;
import com.littlestark.jajan.model.request.user.ProfileUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProfileRepository;
import com.littlestark.jajan.repository.IVerificationProfileRepository;
import com.littlestark.jajan.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService implements IProfileService {


    private IProfileRepository profileRepository;
    private IAuthenticationRepository authenticationRepository;
    private IVerificationProfileRepository verificationProfileRepository;

    @Override
    @Transactional
    public BaseResponse<Object> updateProfile(
            String userId,
            ProfileUserRequest profileUserRequest){
        var user = authenticationRepository.findById(userId);
        var profileEntity = new ProfileEntity();

        if(user.isPresent()) {
            profileEntity = user.get().getProfileEntity();
        }

        profileEntity.setName(profileUserRequest.getName());
        profileEntity.setAddress(profileUserRequest.getAddress());
        profileEntity.setPhoneNumber(profileUserRequest.getPhoneNumber());
        profileEntity.setStoreName(profileUserRequest.getStoreName());
        profileRepository.save(profileEntity);
        return BaseResponse.builder().build();
    }

    @Override
    public BaseResponse<Object> verificationProfile(String userId, Boolean verificationProfile) {
        var user = authenticationRepository.findById(userId);
        var profileEntity = new ProfileEntity();

        if(user.isPresent()) {
            profileEntity = user.get().getProfileEntity();
        }
        profileEntity.setVerificationProfile(verificationProfile);
        profileRepository.save(profileEntity);
        return BaseResponse.builder().build();
    }

    @Override
    public BaseResponse<Object> uploadImageVerificationProfile(String userId, MultipartFile file) {
        var user = authenticationRepository.findById(userId);
        user.ifPresent(userEntity -> {
            try {
                verificationProfileRepository.save(
                        VerificationProfileEntity.builder()
                                .userVerificationProfile(userEntity)
                                .imageData(Utils.compressImage(file.getBytes()))
                                .build()
                );
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        return BaseResponse.builder().build();
    }

    @Override
    public BaseResponse<Object> getAllImageVerificationProfile() {
        List<VerificationProfileEntity> verificationProfile = verificationProfileRepository.findAll();
        return BaseResponse.builder()
                .data(verificationProfile)
                .build();
    }
}
