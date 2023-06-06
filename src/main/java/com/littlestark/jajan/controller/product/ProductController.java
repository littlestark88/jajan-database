package com.littlestark.jajan.controller.product;

import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/jajan/v1/product")
public class ProductController {

    private IProductService productService;

    @PostMapping(
            value = "/{userId}/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<Object> registerUser(
            @PathVariable String userId,
            @RequestBody ProductRequest productRequest) {
        BaseResponse<Object> product = productService.createProduct(userId, productRequest);
        return BaseResponse.builder()
                .code(200)
                .status("Success")
                .message(product.getMessage())
                .data(null)
                .build();
    }
}
