package com.littlestark.jajan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "_profile_table")
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserEntity userProfile;

    @Column(name = "verification_profile")
    private boolean isVerificationProfile;

    @Column(name = "date_Profile")
    private LocalDateTime dateRegister;
}
