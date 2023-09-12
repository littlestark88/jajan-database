package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.StoreEntity;
import com.littlestark.jajan.model.response.store.StoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreRepository extends JpaRepository<StoreEntity, String> {

//    @Query("SELECT new com.littlestark.jajan.model.response.store.StoreResponse" +
//            "(s.id, s.nameStore, s.address, s.district, s.regency, s.userStore.id) " +
//            "FROM StoreEntity s ")
//    List<StoreResponse> findStoreList();
}
