package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest, MultipartFile imageProduct) throws IOException;
    BaseResponse<Object> getProductByUserId(String userId);
}
