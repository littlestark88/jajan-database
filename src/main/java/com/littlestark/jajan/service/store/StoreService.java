package com.littlestark.jajan.service.store;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.request.store.StoreRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.store.StoreResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IStoreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@AllArgsConstructor
public class StoreService implements IStoreService{

    @Autowired
    private IStoreRepository storeRepository;

    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Override
    public BaseResponse<Object> createStore(String userId, StoreRequest storeRequest, MultipartFile imageStore) throws IOException {
        var userEntity = authenticationRepository.findById(userId).orElseThrow();

        var store = StoreEntity.builder()
                .nameStore(storeRequest.getNameStore())
                .address(storeRequest.getAddress())
                .district(storeRequest.getDistrict())
                .regency(storeRequest.getRegency())
                .userStore(userEntity)
                .imageStore(imageStore.getBytes())
                .isVerificationStore(false)
                .build();

        storeRepository.save(store);

        return BaseResponse
                .builder()
                .message("Berhasil membuat toko")
                .build();
    }

    @Override
    public BaseResponse<Object> changeNameStore(String userId, String nameStore) {
        authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        var store = StoreEntity.builder()
                .nameStore(nameStore)
                .build();

        storeRepository.save(store);
        return BaseResponse.builder()
                .message("Berhasil ganti nama toko")
                .build();
    }

    @Override
    public BaseResponse<Object> verificationStore(String userId, boolean isVerificationStore) {
        authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        var store = StoreEntity.builder()
                .isVerificationStore(isVerificationStore)
                .build();

        storeRepository.save(store);
        return BaseResponse.builder()
                .message("Berhasil verification toko")
                .build();
    }

    @Override
    public BaseResponse<Object> uploadImageStore(String userId, MultipartFile imageStore) throws IOException {
        authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User tidak ada"));

        var store = StoreEntity.builder()
                .imageStore(imageStore.getBytes())
                .build();

        storeRepository.save(store);
        return BaseResponse.builder()
                .message("Berhasil upload image")
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<Object> getStoreList() {

//        var store = storeRepository.findAll().stream().map( storeEntity -> StoreResponse
//                .builder()
//                .id(storeEntity.getId())
//                .nameStore(storeEntity.getNameStore())
//                .district(storeEntity.getDistrict())
//                .regency(storeEntity.getRegency())
//                .userId(storeEntity.getUserStore().getId())
//                .build()).toList();

        return BaseResponse.builder()
//                .data(store)
                .build();
    }

    @Override
    public StoreEntity getStoreImageById(String id) {
        return storeRepository.findById(id).get();
    }
}
