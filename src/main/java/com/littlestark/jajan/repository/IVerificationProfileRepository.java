package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.VerificationProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVerificationProfileRepository extends JpaRepository<VerificationProfileEntity, String> {
}
