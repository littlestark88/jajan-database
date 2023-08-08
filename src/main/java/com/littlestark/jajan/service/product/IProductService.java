package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;

import java.util.Optional;


public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest);
    BaseResponse<Object> getProductByUserId(String userId);
}
