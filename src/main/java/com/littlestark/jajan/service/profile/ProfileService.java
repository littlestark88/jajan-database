package com.littlestark.jajan.service.profile;

import com.littlestark.jajan.model.entity.ProfileEntity;
import com.littlestark.jajan.model.request.user.ProfileUserRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService implements IProfileService {

    private IProfileRepository profileRepository;
    private IAuthenticationRepository authenticationRepository;

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
}
