package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, String> {
    Optional<EmailAuth> findByIdAndExpirationDateAfterAndExpired(String authTokenId, LocalDateTime now, boolean expired);

    Optional<EmailAuth> findByEmail(String email);
    void deleteByEmail(String email);
}
