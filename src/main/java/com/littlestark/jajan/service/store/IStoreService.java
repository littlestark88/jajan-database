package com.littlestark.jajan.service.store;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.request.store.StoreRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IStoreService {

    BaseResponse<Object> createStore(
            String userId,
            StoreRequest storeRequest,
            MultipartFile imageStore
    ) throws IOException;

    BaseResponse<Object> changeNameStore(
            String userId,
            String nameStore
    );

    BaseResponse<Object> verificationStore(
            String userId,
            boolean isVerificationStore
    );

    BaseResponse<Object> uploadImageStore(
            String userId,
            MultipartFile imageStore
    ) throws IOException;

    BaseResponse<Object> getStoreList();

    StoreEntity getStoreImageById(String id);
}
