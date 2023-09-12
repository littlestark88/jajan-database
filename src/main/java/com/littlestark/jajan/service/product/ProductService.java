package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.request.product.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public BaseResponse<Object> createProduct(String userId, ProductRequest productRequest, MultipartFile imageProduct) throws IOException {

        var userEntity = authenticationRepository.findById(userId).orElseThrow();
        var product = ProductEntity
                .builder()
                .titleName(productRequest.getTitleProduct())
                .descriptionProduct(productRequest.getDescriptionProduct())
                .typeProduct(productRequest.getTypeProduct())
                .price(productRequest.getPriceProduct())
                .imageProduct(imageProduct.getBytes())
                .categoryProduct(productRequest.getCategoryProduct())
                .userProduct(userEntity)
                .build();

        productRepository.save(product);

        return BaseResponse
                .builder()
                .message("Berhasil menambahkan product baru")
                .build();
    }

    @Override
    @Transactional
    public BaseResponse<Object> getProductByUserId(String userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        var product = productRepository.findProductById(userId, pageable);

        return BaseResponse.builder()
                .data(product)
                .build();
    }

    @Override
    public ProductEntity getImageById(String id) {
        return productRepository.findById(id).get();
    }
}
