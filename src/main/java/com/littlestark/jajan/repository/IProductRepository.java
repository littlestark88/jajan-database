package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {
}
