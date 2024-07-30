package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStoreRepository extends JpaRepository<StoreEntity, String> {

    Optional<StoreEntity> findFirstByUserStoreAndId(UserEntity user, String userId);

    Page<StoreEntity> findByIsVerificationStoreFalse(Pageable pageable);
    Page<StoreEntity> findByNameStoreContaining(String nameStore, Pageable pageable);
//    @Query("SELECT s FROM StoreEntity s JOIN s.product p WHERE p.nameProduct = :name OR s.nameStore = :name")
//    @Query("SELECT s FROM StoreEntity s JOIN s.product p WHERE s.nameStore = :name OR p.nameProduct= :name")
    @Query("SELECT s FROM StoreEntity s JOIN s.product p WHERE s.nameStore = :name OR p.nameProduct= :name")
//    @Query("SELECT s FROM StoreEntity WHERE nameStore = :name" +
//            "UNION ALL " +
//            "SELECT p FROM ProductEntity WHERE nameProduct = :name")
    Page<StoreEntity> findByNameStoreOrNameProductContaining(@Param("name") String name, Pageable pageable);

//    @Query("SELECT s FROM StoreEntity s WHERE s.userStore.id = :userId")
    Optional<StoreEntity> findByUserStoreId(@Param("userId")String userId);
}
