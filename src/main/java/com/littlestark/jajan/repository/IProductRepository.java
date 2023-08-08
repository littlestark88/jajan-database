package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.ProductEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import com.littlestark.jajan.model.response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String> {
//    Optional<List<ProductEntity>> findFirstByUserAndId(UserEntity user, String userId);
    @Query("SELECT new com.littlestark.jajan.model.response.ProductResponse" +
            "(p.id, p.titleName, p.descriptionProduct, p.typeProduct, p.price, p.imageProduct, p.categoryProduct, p.userProduct.id) " +
            "FROM ProductEntity p " +
            "WHERE p.userProduct.id = :userId")
    List<ProductResponse> findProductById(@Param("userId")String userId);
}
