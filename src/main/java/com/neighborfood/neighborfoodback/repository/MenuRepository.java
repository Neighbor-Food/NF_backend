package com.neighborfood.neighborfoodback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.entity.Restaurant;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

    List<Menu> findAllByRestaurant(Restaurant restaurant);


}
