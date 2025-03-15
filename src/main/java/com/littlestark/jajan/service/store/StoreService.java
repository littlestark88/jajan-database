package com.littlestark.jajan.service.store;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.request.store.CreateStoreRequest;
import com.littlestark.jajan.model.request.store.UpdateStoreRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListByNameRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.store.StoreResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IStoreRepository;
import com.littlestark.jajan.utils.ResourceValue;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Base64;

@Service
@AllArgsConstructor
@Slf4j
public class StoreService implements IStoreService{

    @Autowired
    @Transient
    private IStoreRepository storeRepository;

    @Autowired
    @Transient
    private IAuthenticationRepository authenticationRepository;
    final private ResourceValue resourceValue;

    @Override
    @Transactional
    public BaseResponse<Object> createStore(String userId, CreateStoreRequest createStoreRequest) {
        var user = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        StoreResponse storeResponse = null;
        var isSuccess = false;
        if (user.getStoreEntity() == null) {
            if(createStoreRequest.getImageStore().length() > 1000000) {
                message = resourceValue.getImageMaxSize();
            } else {
                byte[] imageStore = Base64.getDecoder().decode(createStoreRequest.getImageStore());
                var store = StoreEntity.builder()
                        .nameStore(createStoreRequest.getNameStore())
                        .address(createStoreRequest.getAddress())
                        .isVerificationStore(false)
                        .isCreatedStore(true)
                        .imageStore(imageStore)
                        .userStore(user)
                        .build();
                storeRepository.save(store);
                message = resourceValue.getSuccessCreateStore();
                isSuccess = true;
                storeResponse = StoreResponse
                        .builder()
                        .isCreatedStore(store.getIsCreatedStore())
                        .build();
            }
        } else {
            message = resourceValue.getCreatedStore();
        }

        return BaseResponse
                .builder()
                .data(storeResponse)
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> updateStore(String userId, UpdateStoreRequest updateStoreRequest) {
        var userEntity = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var store = storeRepository.findFirstByUserStoreAndId(userEntity, userEntity.getStoreEntity().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var message = resourceValue.getEmptyString();
        var isSuccess = false;
        if(updateStoreRequest.getImageStore().length() > 1000000) {
            message = resourceValue.getImageMaxSize();
        } else {
            byte[] imageStore = Base64.getDecoder().decode(updateStoreRequest.getImageStore());
            store.setNameStore(updateStoreRequest.getNameStore());
            store.setAddress(updateStoreRequest.getAddress());
            store.setImageStore(imageStore);

            message = resourceValue.getSuccessUpdateStore();
            isSuccess = true;
            storeRepository.save(store);

        }

        return BaseResponse.builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> verificationStore(String userId, boolean isVerificationStore) {
        var userEntity = authenticationRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));
        var store = storeRepository.findFirstByUserStoreAndId(userEntity, userEntity.getStoreEntity().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));

        store.setIsVerificationStore(isVerificationStore);

        storeRepository.save(store);
        return BaseResponse.builder()
                .message(resourceValue.getSuccessVerificationStore())
                .isSuccess(true)
                .build();
    }

    @Override
    public BaseResponse<Object> getVerificationStoreList(VerificationStoreListRequest verificationStoreListRequest) {

        PageRequest paging = PageRequest.of(verificationStoreListRequest.getPage(), verificationStoreListRequest.getSize());
        var store = storeRepository.findByIsVerificationStoreFalse(paging).stream().map( storeEntity -> StoreResponse
                .builder()
                .id(storeEntity.getId())
                .nameStore(storeEntity.getNameStore())
                .imageStore(Arrays.toString(storeEntity.getImageStore()))
                .isVerificationStore(storeEntity.getIsVerificationStore())
                .userId(storeEntity.getUserStore().getId())
                .build()).toList();

        return BaseResponse.builder()
                .data(store)
                .isSuccess(true)
                .build();
    }

    @Override
    public BaseResponse<Object> getStoreByUserId(String userId) {
        var store = storeRepository.findByUserStoreId(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, resourceValue.getUserNotFound()));

        var data = StoreEntity.builder()
                .nameStore(store.getNameStore())
                .address(store.getAddress())
                .imageStore(store.getImageStore())
                .id(store.getId())
                .isVerificationStore(store.getIsVerificationStore())
                .isCreatedStore(store.getIsCreatedStore())
                .build();
        return BaseResponse.builder()
                .data(data)
                .isSuccess(true)
                .message("Berhasil mendapatkan data store")
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<Object> getStoreList(VerificationStoreListRequest verificationStoreListRequest) {
        PageRequest paging = PageRequest.of(verificationStoreListRequest.getPage(), verificationStoreListRequest.getSize());
        var store = storeRepository.findAll(paging).stream().map( storeEntity -> StoreResponse
                .builder()
                .id(storeEntity.getId())
                .nameStore(storeEntity.getNameStore())
                .imageStore(Arrays.toString(storeEntity.getImageStore()))
                .isVerificationStore(storeEntity.getIsVerificationStore())
                .build()).toList();

        return BaseResponse.builder()
                .data(store)
                .isSuccess(true)
                .build();
    }

    @Override
    public BaseResponse<Object> getStoreListByName(VerificationStoreListByNameRequest verificationStoreListByNameRequest) {
        PageRequest paging = PageRequest.of(verificationStoreListByNameRequest.getPage(), verificationStoreListByNameRequest.getSize());
        var store = storeRepository.findByNameStoreContaining(verificationStoreListByNameRequest.getName(), paging).stream().map( storeEntity -> StoreResponse
                .builder()
                .id(storeEntity.getId())
                .nameStore(storeEntity.getNameStore())
                .imageStore(Arrays.toString(storeEntity.getImageStore()))
                .isVerificationStore(storeEntity.getIsVerificationStore())
                .userId(storeEntity.getUserStore().getId())
                .build()).toList();

        return BaseResponse.builder()
                .data(store)
                .isSuccess(true)
                .build();
    }

    @Override
    public BaseResponse<Object> getStoreOrProductListByName(VerificationStoreListByNameRequest verificationStoreListByNameRequest) {
        PageRequest paging = PageRequest.of(verificationStoreListByNameRequest.getPage(), verificationStoreListByNameRequest.getSize());
        var store = storeRepository.findByNameStoreOrNameProductContaining(verificationStoreListByNameRequest.getName(), paging).stream().map( storeEntity -> StoreResponse
                .builder()
                .id(storeEntity.getId())
                .nameStore(storeEntity.getNameStore())
                .imageStore(Arrays.toString(storeEntity.getImageStore()))
                .isVerificationStore(storeEntity.getIsVerificationStore())
                .userId(storeEntity.getUserStore().getId())
                .build()).toList();

        return BaseResponse.builder()
                .data(store)
                .isSuccess(true)
                .build();
    }

}
