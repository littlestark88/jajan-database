package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private IAuthenticationRepository authenticationRepository;

    private IProductRepository productRepository;

    @Override
    public BaseResponse<Object> createProduct(String userId, ProductRequest productRequest) {

        var userEntity = authenticationRepository.findById(userId).orElseThrow();

        var product = ProductEntity
                .builder()
                .titleName(productRequest.getTitleProduct())
                .descriptionProduct(productRequest.getDescriptionProduct())
                .typeProduct(productRequest.getTypeProduct())
                .price(productRequest.getPriceProduct())
                .imageProduct(productRequest.getImageProduct())
                .categoryProduct(productRequest.getCategoryProduct())
                .userProduct(userEntity)
                .build();

        productRepository.save(product);

        return BaseResponse
                .builder()
                .message("Berhasil menambahkan product baru")
                .build();
    }
}
