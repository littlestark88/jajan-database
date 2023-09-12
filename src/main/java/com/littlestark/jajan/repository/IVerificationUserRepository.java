package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.VerificationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVerificationUserRepository extends JpaRepository<VerificationUserEntity, String> {
}
