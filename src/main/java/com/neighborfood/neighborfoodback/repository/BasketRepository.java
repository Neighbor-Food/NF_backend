package com.neighborfood.neighborfoodback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neighborfood.neighborfoodback.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

    List<Basket> findByBoardId(Integer boardId);

    List<Basket> findByMemberMember_no(Integer member_no);
    
}
