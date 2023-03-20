package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthenticationRepository extends JpaRepository <UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
