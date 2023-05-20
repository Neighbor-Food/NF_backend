package com.neighborfood.neighborfoodback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neighborfood.neighborfoodback.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer>{
    
}
