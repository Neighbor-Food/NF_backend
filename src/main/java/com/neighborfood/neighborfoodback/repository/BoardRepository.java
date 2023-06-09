package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByMember(Member member);
    // category 에 대한 like %키워드%
    List<Board> findByCategoryContaining(String category);
}
