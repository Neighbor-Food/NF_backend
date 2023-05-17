package com.neighborfood.neighborfoodback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.dto.ResponseListDTO;
import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.service.MenuService;
import com.neighborfood.neighborfoodback.service.RestaurantService;

@RequestMapping("api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/list/{restaurant_on}")
    public ResponseEntity<?> getList(){
        try{
            List<Menu> list = menuService.getList(); //todd menuService.getList 작성 필요
            ResponseListDTO<Menu> responseDTO = ResponseListDTO.<Menu>builder()
                        .result("success : get menu list(query : restaurant no)")
                        .data(list)
                        .build();
            return ResponseEntity.ok().body(responseDTO);
        }
        catch(Exception e){
            // 테스트 종료 후 수정 필요 (여기서 부터)
            System.out.println("에러 발생");
            System.out.println(e);
            //(여기까지)
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
