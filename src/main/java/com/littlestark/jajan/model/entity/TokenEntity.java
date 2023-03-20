package com.littlestark.jajan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "_token")
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    public String token;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    private Boolean expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserEntity userEntity;

}
