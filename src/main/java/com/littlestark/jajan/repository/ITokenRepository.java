package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepository extends JpaRepository<TokenEntity, String> {
}
