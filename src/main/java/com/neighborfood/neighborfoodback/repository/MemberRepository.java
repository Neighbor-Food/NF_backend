package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    Boolean existsByEmail(String email);
    Member findByEmailAndPassword(String email, String password);
}