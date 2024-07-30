package com.littlestark.jajan.controller.product;

import com.littlestark.jajan.model.request.product.ProductListRequest;
import com.littlestark.jajan.model.request.product.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.product.IProductService;
import jakarta.websocket.server.PathParam;
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
            @RequestBody ProductRequest productRequest) {
        BaseResponse<Object> product = productService.createProduct(userId, productRequest);
        return BaseResponse.builder()
                .message(product.getMessage())
                .isSuccess(product.isSuccess())
                .data(null)
                .build();
    }

    @PutMapping(
            value = "/{productId}/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> updateProduct(
            @PathVariable String productId,
            @RequestBody ProductRequest productRequest) {
        BaseResponse<Object> product = productService.updateProduct(productId, productRequest);
        return BaseResponse.builder()
                .message(product.getMessage())
                .isSuccess(product.isSuccess())
                .data(null)
                .build();
    }

    @DeleteMapping(
            value = "/{productId}/delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> deleteProduct(
            @PathVariable String productId) {
        BaseResponse<Object> product = productService.deleteProduct(productId);
        return BaseResponse.builder()
                .message(product.getMessage())
                .isSuccess(product.isSuccess())
                .data(null)
                .build();
    }

    @GetMapping(
            value = "/{storeId}/list",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE
            }
    )
    public BaseResponse<Object> getProductList(
            @PathVariable String storeId,
            @RequestBody ProductListRequest productListRequest) {
        BaseResponse<Object> product = productService.getProductList(productListRequest, storeId);
        return BaseResponse.builder()
                .message("")
                .data(product.getData())
                .isSuccess(product.isSuccess())
                .build();
    }

}
