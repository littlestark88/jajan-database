package com.littlestark.jajan.controller.store;

import com.littlestark.jajan.model.request.store.CreateStoreRequest;
import com.littlestark.jajan.model.request.store.UpdateStoreRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListByNameRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.store.IStoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/store")
public class StoreController {

    private IStoreService storeService;

    @PostMapping(
            value = "/{userId}/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> createStore(
            @PathVariable String userId,
            @RequestBody CreateStoreRequest createStoreRequest) {
        BaseResponse<Object> store = storeService.createStore(userId, createStoreRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .data(null)
                .isSuccess(store.isSuccess())
                .build();
    }

    @PutMapping(
            value = "/{userId}/verification",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> verificationStore(
            @PathVariable String userId,
            @RequestParam("value") Boolean isVerificationStore) {
        BaseResponse<Object> store = storeService.verificationStore(userId, isVerificationStore);
        return BaseResponse.builder()
                .message(store.getMessage())
                .data(null)
                .isSuccess(store.isSuccess())
                .build();
    }

    @PostMapping(
            value = "/{userId}/update",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> updateStore(
            @PathVariable String userId,
            @RequestBody UpdateStoreRequest updateStoreRequest) {
        BaseResponse<Object> store = storeService.updateStore(userId, updateStoreRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .data(null)
                .isSuccess(store.isSuccess())
                .build();
    }

    @GetMapping(
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreList(
            @RequestBody VerificationStoreListRequest verificationStoreListRequest
            ) {
        var store = storeService.getStoreList(verificationStoreListRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .isSuccess(store.isSuccess())
                .data(store.getData())
                .build();
    }

    @GetMapping(
            value = "/list-verification",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreListVerification(
            @RequestBody VerificationStoreListRequest verificationStoreListRequest
    ) {
        var store = storeService.getVerificationStoreList(verificationStoreListRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .isSuccess(store.isSuccess())
                .data(store.getData())
                .build();
    }

    @GetMapping(
            value = "/list-store-by-name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreListByName(
            @RequestBody VerificationStoreListByNameRequest verificationStoreListByNameRequest
    ) {
        var store = storeService.getStoreListByName(verificationStoreListByNameRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .isSuccess(store.isSuccess())
                .data(store.getData())
                .build();
    }

    @GetMapping(
            value = "/list-store-product-by-name",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreProductListByName(
            @RequestBody VerificationStoreListByNameRequest verificationStoreListByNameRequest
    ) {
        var store = storeService.getStoreOrProductListByName(verificationStoreListByNameRequest);
        return BaseResponse.builder()
                .message(store.getMessage())
                .isSuccess(store.isSuccess())
                .data(store.getData())
                .build();
    }

    @GetMapping(
            value = "/{userId}/get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreByUserId(
            @PathVariable String userId
    ) {
        var store = storeService.getStoreByUserId(userId);
        return BaseResponse.builder()
                .message(store.getMessage())
                .isSuccess(store.isSuccess())
                .data(store.getData())
                .build();
    }

}
