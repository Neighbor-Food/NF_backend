package com.neighborfood.neighborfoodback.repository;

import com.neighborfood.neighborfoodback.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
