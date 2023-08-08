package com.littlestark.jajan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;

    private String titleName;

    private String descriptionProduct;

    private String typeProduct;

    private Long price;

    private String imageProduct;

    private String categoryProduct;

    private String userId;
}
