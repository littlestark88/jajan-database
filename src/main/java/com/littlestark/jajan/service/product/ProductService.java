package com.littlestark.jajan.service.product;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.request.user.ProductRequest;
import com.littlestark.jajan.model.response.BaseResponse;
import com.littlestark.jajan.repository.IAuthenticationRepository;
import com.littlestark.jajan.repository.IProductRepository;
import com.littlestark.jajan.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private IAuthenticationRepository authenticationRepository;

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
    public BaseResponse<Object> getProductByUserId(String userId) {

//        var user = authenticationRepository.findById(userId).orElseThrow();
        var product = productRepository.findProductById(userId);
//        var user = productRepository.findFirstByUserAndId(userEntity, userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product kosong"));
        return BaseResponse.builder()
                .data(product)
                .build();
    }

    @Override
    public ProductEntity getImageById(String id) {
        return productRepository.findById(id).get();
    }
}
