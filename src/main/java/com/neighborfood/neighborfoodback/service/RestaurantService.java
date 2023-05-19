package com.neighborfood.neighborfoodback.service;

import com.neighborfood.neighborfoodback.entity.Board;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository repository){
        this.restaurantRepository = repository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }


}
