package com.littlestark.jajan.model.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String titleProduct;

    private String descriptionProduct;

    private String typeProduct;

    private Long priceProduct;

    private String categoryProduct;
}
