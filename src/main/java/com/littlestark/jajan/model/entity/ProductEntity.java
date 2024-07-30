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

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "description_product")
    private String descriptionProduct;

    @Column(name = "price")
    private Long price;

    @Column(name = "image_product", length = 1000)
    private byte[] imageProduct;

    @Column(name = "category_product")
    private String categoryProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "store_id",
            referencedColumnName = "id"
    )
    private StoreEntity storeProduct;
}
