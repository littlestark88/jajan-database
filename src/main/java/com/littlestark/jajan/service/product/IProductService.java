package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.request.product.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;


public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest, MultipartFile imageProduct) throws IOException;
    BaseResponse<Object> getProductByUserId(String userId, int page, int size);
    ProductEntity getImageById(String id);
}
