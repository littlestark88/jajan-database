package com.littlestark.jajan.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@Entity
@Table(name = "_product_table")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "title_product")
    private String titleName;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "type_product")
    private String typeProduct;

    @Column(name = "price")
    private Long price;

    @Column(name = "image_product")
    private String imageProduct;

    @Column(name = "category_product")
    private String categoryProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity userProduct;
}
