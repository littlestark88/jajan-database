package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findFirstByUserAndId(UserEntity user, String userId);
}
