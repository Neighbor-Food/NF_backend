package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    // 음식점 리스트 조회
    @GetMapping("/getList")
    public ResponseEntity<?> getList(){
        List<Restaurant> list = restaurantService.getList();
        ResponseListDTO<Restaurant> responseDTO = ResponseListDTO.<Restaurant>builder()
                .result("success")
                .data(list)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 특정 음식점 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable("id") Integer id){
        Restaurant restaurant = restaurantService.getRestaurant(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .data(restaurant)
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}
