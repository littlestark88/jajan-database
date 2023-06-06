package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthenticationRepository extends JpaRepository <UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
