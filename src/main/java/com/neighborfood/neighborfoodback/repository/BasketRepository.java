package com.neighborfood.neighborfoodback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neighborfood.neighborfoodback.entity.Basket;
import com.neighborfood.neighborfoodback.entity.Board;

public interface BasketRepository extends JpaRepository<Basket, Integer>{

    List<Basket> findByBoard(Board board);
    
}
