package com.littlestark.jajan.model.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String nameProduct;

    private String descriptionProduct;

    private String imageProduct;

    private Long priceProduct;

    private String categoryProduct;
}
