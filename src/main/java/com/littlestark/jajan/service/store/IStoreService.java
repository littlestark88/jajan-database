package com.littlestark.jajan.service.store;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.littlestark.jajan.model.request.store.CreateStoreRequest;
import com.littlestark.jajan.model.request.store.UpdateStoreRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListByNameRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListRequest;
import com.littlestark.jajan.model.response.BaseResponse;

public interface IStoreService {

    BaseResponse<Object> createStore(
            String userId,
            CreateStoreRequest createStoreRequest
    );
    BaseResponse<Object> updateStore(
            String userId,
            UpdateStoreRequest updateStoreRequest
    );
    BaseResponse<Object> verificationStore(
            String userId,
            boolean isVerificationStore
    );
    BaseResponse<Object> getVerificationStoreList(VerificationStoreListRequest verificationStoreListRequest);

    BaseResponse<Object> getStoreByUserId(String userId);
    BaseResponse<Object> getStoreList(VerificationStoreListRequest verificationStoreListRequest);
    BaseResponse<Object> getStoreListByName(VerificationStoreListByNameRequest verificationStoreListByNameRequest);
    BaseResponse<Object> getStoreOrProductListByName(VerificationStoreListByNameRequest verificationStoreListByNameRequest);

}
