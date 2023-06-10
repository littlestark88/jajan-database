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
@Table(name = "_verification_profile_table")
@NoArgsConstructor
@AllArgsConstructor
public class VerificationProfileEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

//    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @OneToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity userVerificationProfile;
}
