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

    @Column(name = "district")
    private String district;

    @Column(name = "regency")
    private String regency;

    @Column(name = "verification_store")
    private boolean isVerificationStore;

    @Column(name = "image_store")
    @Lob
    private byte[] imageStore;

    @OneToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity userStore;
}
