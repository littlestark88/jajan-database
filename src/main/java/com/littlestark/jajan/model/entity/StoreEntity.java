package com.littlestark.jajan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "_store_table")
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name_store")
    private String nameStore;

    @Column(name = "address")
    private String address;

    @Column(name = "verification_store")
    private Boolean isVerificationStore;

    @Column(name = "image_store", length = 1000)
    private byte[] imageStore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity userStore;

    @OneToMany(mappedBy = "storeProduct", cascade = CascadeType.ALL)
    private List<ProductEntity> product;
}
