package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.request.product.ProductListRequest;
import com.littlestark.jajan.model.request.product.ProductRequest;
import com.littlestark.jajan.model.request.store.VerificationStoreListRequest;
import com.littlestark.jajan.model.response.BaseResponse;


public interface IProductService {

    BaseResponse<Object> createProduct(String userId, ProductRequest productRequest);
    BaseResponse<Object> updateProduct(String productId, ProductRequest productRequest);
    BaseResponse<Object> deleteProduct(String productId);
    BaseResponse<Object> getProductList(ProductListRequest productListRequest, String storeId);
}
