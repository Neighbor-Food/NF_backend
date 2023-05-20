package com.neighborfood.neighborfoodback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighborfood.neighborfoodback.dto.MenuDTO;
import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.entity.Restaurant;
import com.neighborfood.neighborfoodback.service.MenuService;
import com.neighborfood.neighborfoodback.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;
    private final RestaurantService retaurantService;

    @Autowired
    public MenuController(MenuService menuService, RestaurantService retaurantService){
        this.menuService = menuService;
        this.retaurantService = retaurantService;
    }

    // 특정 메뉴 조회
    @GetMapping("/{menu_no}")
    public ResponseEntity<?> getMenu(@PathVariable("menu_no") Integer menu_no){
        try{
            System.out.println(menu_no);
            System.out.println(menuService.getMenu(menu_no));
            Menu menu = menuService.getMenu(menu_no);
            MenuDTO.detail menuDTO = Menu.toDetailDTO(menu);

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("success")
                    .data(menuDTO)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    //식당 ID를 통한 메뉴 리스트 조회
    @GetMapping("/restaurant/{restaurant_no}")
    public ResponseEntity<?> getMEnuListByRestaurantId(@PathVariable("restaurant_no") Integer restaurant_no){
        try{
            Restaurant restaurant = retaurantService.getRestaurant(restaurant_no);
            List<Menu> menuList = menuService.getMenuListByRestaurnatId(restaurant);
            List<MenuDTO.info> menuInfoDTOList = Menu.toInfoDTOList(menuList);

            ResponseDTO responseDTO = ResponseDTO.builder()
            .result("success")
            .data(menuInfoDTOList)
            .build();
    return ResponseEntity.ok().body(responseDTO);
        }catch(Exception e){
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .result("fail")
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
