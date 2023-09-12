package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.response.product.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String> {
    @Query("SELECT new com.littlestark.jajan.model.response.product.ProductResponse" +
            "(p.id, p.titleName, p.descriptionProduct, p.typeProduct, p.price, p.categoryProduct) " +
            "FROM ProductEntity p " +
            "WHERE p.userProduct.id = :userId")
    List<ProductResponse> findProductById(@Param("userId")String userId, Pageable pageable);
}
