package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.request.product.ProductListRequest;
import com.littlestark.jajan.model.request.product.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.model.response.product.ProductResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProductRepository;
import com.littlestark.jajan.repository.IStoreRepository;
import com.littlestark.jajan.utils.ResourceValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;
    @Autowired
    private IProductRepository productRepository;
    final private ResourceValue resourceValue;
    @Autowired
    private IStoreRepository storeRepository;

    @Override
    public BaseResponse<Object> createProduct(String userId, ProductRequest productRequest) {

        var message = resourceValue.getEmptyString();
        var isSuccess = false;

        if(productRequest.getImageProduct().length() > 1000000) {
            message = resourceValue.getImageMaxSize();
        } else {
            byte[] imageStore = Base64.getDecoder().decode(productRequest.getImageProduct());
            var userEntity = storeRepository.findById(userId).orElseThrow();
            var product = ProductEntity
                    .builder()
                    .nameProduct(productRequest.getNameProduct())
                    .descriptionProduct(productRequest.getDescriptionProduct())
                    .price(productRequest.getPriceProduct())
                    .imageProduct(imageStore)
                    .categoryProduct(productRequest.getCategoryProduct())
                    .storeProduct(userEntity)
                    .build();

            message = resourceValue.getSuccessCreateProduct();
            isSuccess = true;
            productRepository.save(product);
        }

        return BaseResponse
                .builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> updateProduct(String productId, ProductRequest productRequest) {

        var message = resourceValue.getEmptyString();
        byte[] imageStore = Base64.getDecoder().decode(productRequest.getImageProduct());
        var productData =  productRepository.findById(productId);
        var isSuccess = false;
        if(productRequest.getImageProduct().length() > 1000000) {
            message = resourceValue.getImageMaxSize();
        } else {
            if (productData.isPresent()) {
                var product = ProductEntity
                        .builder()
                        .id(productId)
                        .nameProduct(productRequest.getNameProduct())
                        .descriptionProduct(productRequest.getDescriptionProduct())
                        .price(productRequest.getPriceProduct())
                        .imageProduct(imageStore)
                        .categoryProduct(productRequest.getCategoryProduct())
                        .build();
                isSuccess = true;
                message = resourceValue.getSuccessUpdateProduct();
                productRepository.save(product);
            }
        }

        return BaseResponse
                .builder()
                .message(message)
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> deleteProduct(String productId) {
        var isSuccess = false;
        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
            isSuccess = true;
        }

        return BaseResponse
                .builder()
                .message(resourceValue.getSuccessDeleteProduct())
                .isSuccess(isSuccess)
                .build();
    }

    @Override
    public BaseResponse<Object> getProductList(ProductListRequest productListRequest, String storeId) {
        PageRequest paging = PageRequest.of(productListRequest.getPage(), productListRequest.getSize());

        var store = productRepository.findByStoreProductId(storeId, paging).stream().map(productEntity -> ProductResponse
                .builder()
                .id(productEntity.getId())
                .nameProduct(productEntity.getNameProduct())
                .imageProduct(Arrays.toString(productEntity.getImageProduct()))
                .descriptionProduct(productEntity.getDescriptionProduct())
                .price(productEntity.getPrice())
                .categoryProduct(productEntity.getCategoryProduct())
                .build()).toList();

        return BaseResponse.builder()
                .data(store)
                .isSuccess(true)
                .build();
    }

}
