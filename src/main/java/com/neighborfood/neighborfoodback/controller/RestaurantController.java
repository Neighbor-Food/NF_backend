package com.neighborfood.neighborfoodback.controller;

import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.dto.RestaurantDTO;
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
    public ResponseEntity<?> getList() {
        try {
            // 음식점 리스트 가져오기
            List<Restaurant> list = restaurantService.getList();
            // restaurant info dto list 로 변환
            List<RestaurantDTO.info> restaurantInfoDTOList = Restaurant.toInfoDTOList(list);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(restaurantInfoDTOList)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 특정 음식점 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable("id") Integer id) {
        try {
            // 음식점 가져오기
            Restaurant restaurant = restaurantService.getRestaurant(id);
            // restaurant info dto 로 변환
            RestaurantDTO.info restaurantInfoDTO = Restaurant.toInfoDTO(restaurant);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(restaurantInfoDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 카테고리 별 음식점 리스트 조회
    @GetMapping("/getListByCategory")
    public ResponseEntity<?> getListByCategory(@RequestParam("category") String category) {
        try {
            // 카테고리에 대한 게시글 리스트 가져오기
            List<Restaurant> list = restaurantService.getListByCategory(category);
            // board info dto list 로 변환
            List<RestaurantDTO.info> restaurantInfoDTOList = Restaurant.toInfoDTOList(list);
            // 응답
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(restaurantInfoDTOList)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
