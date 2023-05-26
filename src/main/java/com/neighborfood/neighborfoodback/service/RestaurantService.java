package com.neighborfood.neighborfoodback.service;

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

    public List<Restaurant> getList() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList;
    }

    // 게시글 조회
    public Restaurant getRestaurant(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            // catch exception
            log.warn("restaurant does not exist");
            throw new RuntimeException("restaurant does not exist");
        }
    }

    // 카테고리에 대한 음식점 리스트 조회
    public List<Restaurant> getListByCategory(String category) {
        List<Restaurant> restaurantList = restaurantRepository.findByCategoryContaining(category);
        return restaurantList;
    }
}
