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
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getList(){
        return restaurantRepository.findAll();
    }

    // 게시글 조회
    public Restaurant getRestaurant(Integer id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            return restaurant.get();
        } else {
            log.warn("restaurant does not exists");
            throw new RuntimeException("restaurant does not exists");
        }
    }

    public Restaurant getById(Integer restaurant_no) {
        return null;
    }
}
