package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    // category 에 대한 like %키워드%
    List<Restaurant> findByCategoryContaining(String category);
}
