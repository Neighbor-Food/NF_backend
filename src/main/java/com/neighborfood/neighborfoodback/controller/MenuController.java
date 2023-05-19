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

import com.neighborfood.neighborfoodback.dto.ResponseDTO;
import com.neighborfood.neighborfoodback.entity.Menu;
import com.neighborfood.neighborfoodback.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    // 특정 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenu(@PathVariable("id") Integer id){
        Optional<Menu> menu = menuService.getById(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .data(menu)
                .build();
        
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getMenuListByRestaurantId(@PathVariable("id") Integer id){
        List<Menu> menuList = menuService.getByRestaurantId(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .result("success")
                .data(menuList)
                .build();
        
        return ResponseEntity.ok().body(responseDTO);
    }
}
