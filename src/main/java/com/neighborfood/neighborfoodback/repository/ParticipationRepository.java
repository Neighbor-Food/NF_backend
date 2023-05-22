package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import com.neighborfood.neighborfoodback.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    Integer countByBoard(Board board);

    Boolean existsByBoardAndMember(Board board, Member member);

    Optional<Participation> findByBoardAndMember(Board board, Member member);

    List<Participation> findAllByBoard(Board board);
}
