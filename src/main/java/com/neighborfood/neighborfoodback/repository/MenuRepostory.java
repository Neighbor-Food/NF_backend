package com.neighborfood.neighborfoodback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neighborfood.neighborfoodback.entity.Menu;

public interface MenuRepostory extends JpaRepository<Menu, Integer>{

    List<Menu> findAllByRestaurant_no(Integer restaurant_no);
    
}
