package com.utcb.javaBackendStart.auth.repositories;

import com.utcb.javaBackendStart.auth.entities.PasswordResetCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCodeEntity, String> {
   Optional<PasswordResetCodeEntity> findByEmailAndCode(String email, String code);
    Optional<PasswordResetCodeEntity> findByEmailAndExpiresAtAfter(String email, LocalDateTime now);
}
