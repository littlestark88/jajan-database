package com.littlestark.jajan.controller.product;

import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.product.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/product")
@Slf4j
public class ProductController {

    private IProductService productService;

    @PostMapping(
            value = "/{userId}/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> createProduct(
            @PathVariable String userId,
            @RequestPart("product") ProductRequest productRequest,
            @RequestPart("imageProduct")MultipartFile imageProduct) throws IOException {
        BaseResponse<Object> product = productService.createProduct(userId, productRequest, imageProduct);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(product.getMessage())
                .data(null)
                .build();
    }


    @GetMapping(
            value = "/{userId}/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> getProductByUserId(
            @PathVariable("userId") String userId) {
        BaseResponse<Object> productByUserId = productService.getProductByUserId(userId);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message("")
                .data(productByUserId.getData())
                .build();
    }

    @GetMapping(
            value = "/image-product/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFiles(
            @PathVariable String id
    ) {
        var filesDB = productService.getImageById(id);

        return ResponseEntity.ok()
                .body(filesDB.getImageProduct());

    }
}
