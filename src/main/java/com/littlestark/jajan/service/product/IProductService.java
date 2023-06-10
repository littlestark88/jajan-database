package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;


public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest);
    BaseResponse<Object> getAllProductByUserId(String userId);
}
