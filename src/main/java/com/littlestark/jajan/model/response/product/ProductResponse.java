package com.littlestark.jajan.model.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String nameProduct;

    private String descriptionProduct;

    private String imageProduct;

    private Long price;

    private String categoryProduct;

}
