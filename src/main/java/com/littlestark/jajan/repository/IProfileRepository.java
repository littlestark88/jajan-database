package com.littlestark.jajan.repository;

import com.littlestark.jajan.model.entity.ProfileEntity;
import com.littlestark.jajan.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<ProfileEntity, String> {
}
