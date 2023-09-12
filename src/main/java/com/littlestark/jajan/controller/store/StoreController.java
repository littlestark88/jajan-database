package com.littlestark.jajan.controller.store;

import com.littlestark.jajan.model.request.store.StoreRequest;
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
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> createStore(
            @PathVariable String userId,
            @RequestPart("store") StoreRequest storeRequest,
            @RequestPart("imageStore") MultipartFile imageStore) throws IOException {
        BaseResponse<Object> store = storeService.createStore(userId, storeRequest, imageStore);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(store.getMessage())
                .data(null)
                .build();
    }

    @PostMapping(
            value = "/{userId}/change-name",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> postChangeNameStore(
            @PathVariable String userId,
            @RequestParam("nameStore") String nameStore) {
        BaseResponse<Object> store = storeService.changeNameStore(userId, nameStore);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(store.getMessage())
                .data(null)
                .build();
    }

    @PostMapping(
            value = "/{userId}/verification",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> verificationStore(
            @PathVariable String userId,
            @RequestParam("value") Boolean isVerificationStore) {
        BaseResponse<Object> store = storeService.verificationStore(userId, isVerificationStore);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(store.getMessage())
                .data(null)
                .build();
    }

    @PostMapping(
            value = "/{userId}/upload-image",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> uploadImageStore(
            @PathVariable String userId,
            @RequestParam("value") MultipartFile imageStore) throws IOException {
        BaseResponse<Object> store = storeService.uploadImageStore(userId, imageStore);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(store.getMessage())
                .data(null)
                .build();
    }

    @GetMapping(
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getStoreList() {
        var product = storeService.getStoreList();
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message("")
                .data(product.getData())
                .build();
    }

    @GetMapping(
            value = "/image-store/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFiles(
            @PathVariable String id
    ) {
        var filesDB = storeService.getStoreImageById(id);

        return ResponseEntity.ok()
                .body(filesDB.getImageStore());

    }

}
